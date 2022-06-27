rootProject.name = "my-tanhua"
include("my-sso")
include("my-tanhua-dubbo")
include("my-tanhua-dubbo:my-tanhua-service")
findProject(":my-tanhua-dubbo:my-tanhua-service")?.name = "my-tanhua-service"
