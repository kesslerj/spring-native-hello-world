package com.myorg;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import software.amazon.awscdk.BundlingOptions;
import software.amazon.awscdk.BundlingOutput;
import software.amazon.awscdk.DockerVolume;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class CdkStack extends Stack {

    public CdkStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public CdkStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        List<String> functionOnePackagingInstructions = Arrays.asList(
            "/bin/sh",
            "-c",
            "cd FunctionOne " +
                "&& mvn clean install " +
                "&& cp /asset-input/FunctionOne/target/functionone.jar /asset-output/"
        );

        BundlingOptions.Builder builderOptions = BundlingOptions.builder()
            .command(functionOnePackagingInstructions)
            .image(Runtime.JAVA_17.getBundlingImage())
            .volumes(Collections.singletonList(
                // Mount local .m2 repo to avoid download all the dependencies again inside the container
                DockerVolume.builder()
                    .hostPath(System.getProperty("user.home") + "/.m2/")
                    .containerPath("/root/.m2/")
                    .build()
            ))
            .user("root")
            .outputType(BundlingOutput.ARCHIVED);

        Function functionOne = new Function(this, "FunctionOne", FunctionProps.builder()
            .runtime(Runtime.JAVA_11)
            .code(Code.fromAsset("../software/", AssetOptions.builder()
                .bundling(builderOptions
                    .command(functionOnePackagingInstructions)
                    .build())
                .build()))
            .handler("helloworld.App")
            .memorySize(1024)
            .timeout(Duration.seconds(10))
            .logRetention(RetentionDays.ONE_WEEK)
            .build());

        HttpApi httpApi = new HttpApi(this, "sample-api", HttpApiProps.builder()
            .apiName("sample-api")
            .build());

        httpApi.addRoutes(AddRoutesOptions.builder()
            .path("/one")
            .methods(singletonList(HttpMethod.GET))
            .integration(new HttpLambdaIntegration("functionOne", functionOne, HttpLambdaIntegrationProps.builder()
                .payloadFormatVersion(PayloadFormatVersion.VERSION_2_0)
                .build()))
            .build());

        httpApi.addRoutes(AddRoutesOptions.builder()
            .path("/two")
            .methods(singletonList(HttpMethod.GET))
            .integration(new HttpLambdaIntegration("functionTwo", functionTwo, HttpLambdaIntegrationProps.builder()
                .payloadFormatVersion(PayloadFormatVersion.VERSION_2_0)
                .build()))
            .build());

        new CfnOutput(this, "HttApi", CfnOutputProps.builder()
            .description("Url for Http Api")
            .value(httpApi.getApiEndpoint())
            .build());
}
