This project is to test how to shut down Spring boot instance server with the use of an Api.

Problems:

When there are threads that are not handle by the Spring boot context will shut down but the application will still run
, thus not freeing the JVM and keeping the resources.

Solution:

1.Keep all threads in a ThreadPoolTaskExecutor managed by Spring boot context. See '
com.example.shutdownapi.services.MyThreadService'

2.Or close all thread before shutting down the context.See 'com.example.shutdownapi.components.ContextClosedHandler'

