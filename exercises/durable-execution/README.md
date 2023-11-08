# Exercise 2: Observing Durable Execution
During this exercise, you will

* Create Workflow and Activity loggers 
* Add logging statements to the code
* Add a Timer to the Workflow Definition
* Launch two Workers and run the Workflow
* Kill one of the Workers during Workflow Execution and observe that the remaining Worker completes the execution

Make your changes to the code in the `practice` subdirectory (look for `TODO` comments that will guide you to where you should make changes to the code). If you need a hint or want to verify your changes, look at the complete version in the `solution` subdirectory.

## Prerequisite: Ensure the Microservice is running
If you haven't already started the Microservice in previous exercises, do so in
a separate terminal.

**Note: If you're using the Gitpod environment to run this exercise you can
skip this step. An instance of the Microservice is already running in your
environment**

1. Navigate to the `utilities` directory at the root level of the course
2. Change directoryies into the `microservice directory`
   1. `cd utilities/microservice`
3. Compile the microservice
   1. `mvn clean compile`
4. Start the microservice 
   1. `mvn exec:java -Dexec.mainClass="translationapi.Microservice"`

## Part A: Add Logging to the Workflow Code

1. Edit the `TranslationWorkflowImpl.java` file
2. Define a Workflow logger at the top of the Workflow function
3. Replace the TODO in the `sayHelloGoodbye` method to log a message at the Info level
   1. It should mention that the Workflow function has been invoked
   2. It should also include the variables passed as input
3. Before each call to Activity Methods, log a message at Debug level
   1. This should should identify the word being translated
   2. It should also include a the language code passed as input
4. Save your changes


## Part B: Add Logging to the Activity Code

1. Edit the `TranslationActivitiesImpl.java` file
2. Add an imports for `import org.slf4j.Logger;` and `import org.slf4j.LoggerFactory;` (this allows you to use the slf4j logger, since Activities don't require a specialized logger)
3. Define an Activity logger at the top of the Activity class as an instance variable
4. Replace the TODO in the `translateTerm` method to log a message at the Info level so you'll know when the Activity is invoked. 
   1. Start the log message with the string `[ACTIVITY INVOKED]` as you'll be scanning the logs for this output in the terminal in a later step and need to be able to easily see it.
   2. Include the term being translated and the language code 
4. Optionally, add log statements at the Error level anywhere that the Activity throws an exception
5. Near the bottom of the method, use the Debug level to log the successful translation
   1. Include the translated term
6. Save your changes


## Part C: Add a Timer to the Workflow
You will now add a Timer between the two Activity calls in the Workflow Definition, which will make it easier to observe durable execution in the next section.

1. After the statement where `helloMessage` is defined, but before the statement where
   `goodbyeInput` is defined, add a new statement that logs the message `Sleeping between 
    translation calls` at the info level.
2. Just after the new log statement, use `workflow.Sleep(Duration.ofSeconds(30))` to set a Timer for 30 seconds


## Part D: Observe Durable Execution
It is typical to run Temporal applications using two or more Worker processes. Not only do additional Workers allow the application to scale, it also increases availability since another Worker can take over if a Worker crashes during Workflow Execution. You'll see this for yourself now and will learn more about how Temporal achieves this as you continue through the course.

Before proceeding, make sure that there are no Workers running for this or any previous exercise. Also, please read through all of these instructions before you begin, so that you'll know when and how to react. 

1. Open a terminal, change to the `practice` subdirectory for this exercise, and run `mvn compile` to compile the code.
2. In the same terminal, start the Worker by running `mvn exec:java -Dexec.mainClass="translationworkflow.TranslationWorker"`
3. In another terminal, start a second Worker by running `mvn exec:java -Dexec.mainClass="translationworkflow.TranslationWorker"`
4. In another terminal, execute the Workflow by running `mvn exec:java -Dexec.mainClass="translationworkflow.Starter" -Dexec.args="Stanislav sk"` (replace `Stanislav` with your first name) 
5. Observe the output in the terminal windows used by each worker. 
   6. As soon as you see a log message in one of the Worker terminals indicating that it has started the Timer, locate the terminal with the message `[ACTIVITY INVOKED]...` and press Ctrl-C in _that_ window to kill that Worker process.
7. Switch to the terminal window for the other Worker process. Within a few seconds, you should observe new output, indicating that it has resumed execution of the Workflow.
8. Once you see log output indicating that translation was successful, switch back to the terminal window where you started the Workflow. 

After the final step, you should see the translated Hello and Goodbye messages, which confirms that Workflow Execution completed successfully despite the original Worker being killed.

Since you added logging code to the Workflow and Activity code, take a moment to look at what you see in the terminal windows for each Worker and think about what took place. You may also find it helpful to look at this Workflow Execution in the Web UI.

The microservice for this exercise outputs each successful translation, and if you look at its terminal window, you will see that the service only translated Hello (the first Activity) once, even though the Worker was killed after this translation took place. In other words, Temporal did not re-execute the completed Activity when it restored the state of the Workflow Execution. 

### This is the end of the exercise.
