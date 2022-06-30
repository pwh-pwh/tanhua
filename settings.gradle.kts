rootProject.name = "my-tanhua"
include("my-sso")
include("my-tanhua-dubbo")
include("my-tanhua-dubbo:my-tanhua-service")
findProject(":my-tanhua-dubbo:my-tanhua-service")?.name = "my-tanhua-service"
include("my-tanhua-dubbo:my-tanhua-interface")
findProject(":my-tanhua-dubbo:my-tanhua-interface")?.name = "my-tanhua-interface"
