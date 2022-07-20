package org.coderpwh

import org.apache.zookeeper.server.ServerConfig
import org.apache.zookeeper.server.ZooKeeperServerMain
import org.apache.zookeeper.server.quorum.QuorumPeerConfig
import org.slf4j.LoggerFactory
import org.springframework.context.SmartLifecycle
import org.springframework.util.ErrorHandler
import org.springframework.util.SocketUtils
import java.io.File
import java.util.*

/**
 * from: https://github.com/spring-projects/spring-xd/blob/v1.3.1.RELEASE/spring-xd-dirt/src/main/java/org/springframework/xd/dirt/zookeeper/ZooKeeperUtils.java
 *
 *
 * Helper class to start an embedded instance of standalone (non clustered) ZooKeeper.
 *
 *
 * NOTE: at least an external standalone server (if not an ensemble) are recommended, even for
 * [org.springframework.xd.dirt.server.singlenode.SingleNodeApplication]
 *
 * @author Patrick Peralta
 * @author Mark Fisher
 * @author David Turanski
 */
class EmbeddedZooKeeper : SmartLifecycle {
    /**
     * Returns the port that clients should use to connect to this embedded server.
     *
     * @return dynamically determined client port
     */
    /**
     * ZooKeeper client port. This will be determined dynamically upon startup.
     */
    val clientPort: Int

    /**
     * Whether to auto-start. Default is true.
     */
    private var autoStartup = true

    /**
     * Lifecycle phase. Default is 0.
     */
    private var phase = 0

    /**
     * Thread for running the ZooKeeper server.
     */
    @Volatile
    private var zkServerThread: Thread? = null

    /**
     * ZooKeeper server.
     */
    @Volatile
    private var zkServer: ZooKeeperServerMain? = null

    /**
     * [ErrorHandler] to be invoked if an Exception is thrown from the ZooKeeper server thread.
     */
    private var errorHandler: ErrorHandler? = null
    private var daemon = true

    /**
     * Construct an EmbeddedZooKeeper with a random port.
     */
    constructor() {
        clientPort = SocketUtils.findAvailableTcpPort()
    }

    /**
     * Construct an EmbeddedZooKeeper with the provided port.
     *
     * @param clientPort port for ZooKeeper server to bind to
     */
    constructor(clientPort: Int, daemon: Boolean) {
        this.clientPort = clientPort
        this.daemon = daemon
    }

    /**
     * Specify whether to start automatically. Default is true.
     *
     * @param autoStartup whether to start automatically
     */
    fun setAutoStartup(autoStartup: Boolean) {
        this.autoStartup = autoStartup
    }

    /**
     * {@inheritDoc}
     */
    override fun isAutoStartup(): Boolean {
        return autoStartup
    }

    /**
     * Specify the lifecycle phase for the embedded server.
     *
     * @param phase the lifecycle phase
     */
    fun setPhase(phase: Int) {
        this.phase = phase
    }

    /**
     * {@inheritDoc}
     */
    override fun getPhase(): Int {
        return phase
    }

    /**
     * {@inheritDoc}
     */
    override fun isRunning(): Boolean {
        return zkServerThread != null
    }

    /**
     * Start the ZooKeeper server in a background thread.
     *
     *
     * Register an error handler via [.setErrorHandler] in order to handle
     * any exceptions thrown during startup or execution.
     */
    @Synchronized
    override fun start() {
        if (zkServerThread == null) {
            zkServerThread = Thread(ServerRunnable(), "ZooKeeper Server Starter")
            zkServerThread!!.isDaemon = daemon
            zkServerThread!!.start()
        }
    }

    /**
     * Shutdown the ZooKeeper server.
     */
    @Synchronized
    override fun stop() {
        if (zkServerThread != null) {
            // The shutdown method is protected...thus this hack to invoke it.
            // This will log an exception on shutdown; see
            // https://issues.apache.org/jira/browse/ZOOKEEPER-1873 for details.
            try {
                val shutdown = ZooKeeperServerMain::class.java.getDeclaredMethod("shutdown")
                shutdown.isAccessible = true
                shutdown.invoke(zkServer)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

            // It is expected that the thread will exit after
            // the server is shutdown; this will block until
            // the shutdown is complete.
            zkServerThread = try {
                zkServerThread!!.join(5000)
                null
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                logger.warn("Interrupted while waiting for embedded ZooKeeper to exit")
                // abandoning zk thread
                null
            }
        }
    }

    /**
     * Stop the server if running and invoke the callback when complete.
     */
    override fun stop(callback: Runnable) {
        stop()
        callback.run()
    }

    /**
     * Provide an [ErrorHandler] to be invoked if an Exception is thrown from the ZooKeeper server thread. If none
     * is provided, only error-level logging will occur.
     *
     * @param errorHandler the [ErrorHandler] to be invoked
     */
    fun setErrorHandler(errorHandler: ErrorHandler?) {
        this.errorHandler = errorHandler
    }

    /**
     * Runnable implementation that starts the ZooKeeper server.
     */
    private inner class ServerRunnable : Runnable {
        override fun run() {
            try {
                val properties = Properties()
                val file = File(
                    System.getProperty("java.io.tmpdir")
                            + File.separator + UUID.randomUUID()
                )
                file.deleteOnExit()
                properties.setProperty("dataDir", file.absolutePath)
                properties.setProperty("clientPort", clientPort.toString())
                val quorumPeerConfig = QuorumPeerConfig()
                quorumPeerConfig.parseProperties(properties)
                zkServer = ZooKeeperServerMain()
                val configuration = ServerConfig()
                configuration.readFrom(quorumPeerConfig)
                zkServer!!.runFromConfig(configuration)
            } catch (e: Exception) {
                if (errorHandler != null) {
                    errorHandler!!.handleError(e)
                } else {
                    logger.error("Exception running embedded ZooKeeper", e)
                }
            }
        }
    }

    companion object {
        /**
         * Logger.
         */
        private val logger = LoggerFactory.getLogger(EmbeddedZooKeeper::class.java)
    }
}