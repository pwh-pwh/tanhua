package org.coderpwh.config

import com.aliyun.oss.OSSClient
import com.aliyun.oss.OSSClientBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

/**
 * @author coderpwh
 * @date 2022-06-08 11:12
 * @version 1.0.0 v
 */
@Configuration
@PropertySource("classpath:aliyun.properties")
@ConfigurationProperties(prefix = "aliyun")
class AliyunConfig {
    lateinit var endpoint:String
    lateinit var accessKeyId:String
    lateinit var accessKeySecret:String
    lateinit var bucketName:String
    lateinit var urlPrefix:String
    @Bean
    fun oSSClient() = OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret)
}
