# Age Estimation Workflow
This example provides a Workflow that can estimate someone's age, 
based on their given name. It uses a single Activity implementation, 
which calls a remote API to retrieve this estimation.

In addition to the Workflow, Activity, and Worker, it also includes 
a class that you can use start the Workflow (pass a name to use as 
input on the command line when invoking it). 

To run the example, first launch the Worker by executing the following 
command in a terminal window:

```bash
$ mvn exec:java \
    -Dexec.mainClass="ageestimationworkflow.AgeEstimationWorker"
```

Next, start the Workflow by running the following command in another 
terminal window:

```bash
$ mvn exec:java \
    -Dexec.mainClass="ageestimationworkflow.Starter" \
    -Dexec.args="Betty"
```

This will output a message with the name and estimated age:

```
Betty has an estimated age of 76
```

This example provides tests for the Workflow and Activity code, which 
you may also wish to review. You can run the tests by executing the 
following command:

```bash
$ mvn test
```
