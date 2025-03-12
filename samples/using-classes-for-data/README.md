# Optional: Using Classes for Data

In this example, you will see how classes are used to represent input and output
data of Workflow and Activity Definitions.

## Prerequisite: Ensure the Microservice is running

If you haven't already started the Translation Microservice used by this exercise,
do so in a separate terminal.

1. Navigate to the `utilities` directory at the root level of the course
2. Change directoryies into the `microservice directory`
   1. `cd utilities/microservice`
3. Compile the microservice
   1. `mvn clean compile`
4. Start the microservice
   1. `mvn exec:java -Dexec.mainClass="translationapi.Microservice"`

## Part A: Observing the Defined Classes in Workflows

This sample provides an improved version of the translation Workflow used in
Temporal 101. The Workflow follows the best practice of using classes to represent
input parameters and return values.

Look at the code in the `models` directory to see how the classes are defined for
the Workflows and Activities. After this, look at the `TranslationWorkflow.java`
and `TranslationWorkflowImpl.java` files to see how these values are passed in
and used in the Workflow code. Finally, look at the `Starter.java` to see how
the input parameters are created and passed into the Workflow.

## Part B: Observing the Defined Classes in Activities

Now let's take a look at how we used classes to represent input and output data
in Activity definitions.

Take a look at the `TranslationActivities.java` and `TranslationActivitiesImpl.java`
files to see how the `translateTerm` function takes in the `TranslationActivityInput`
class as an input parameter. Also notice how that function returns a
`TranslationActivityOutput` class for the output.

## Part C: Run the Translation Workflow

To run the workflow:

1. Ensure that your Translation Microservice is running in a separate. See the
   prerequisites at the top of this file for instructions.
2. In another terminal, start the Worker by running `mvn exec:java -Dexec.mainClass="translationworkflow.TranslationWorker"`
3. In another terminal, execute the Workflow by running `mvn exec:java -Dexec.mainClass="translationworkflow.Starter" -Dexec.args="Mason de"` (replace `Mason` with your first name), which should display customized greeting and farewell messages in German.

It's common for a single Workflow Definition to be executed multiple times, each time using a different input. Feel free to experiment with this by specifying a different language code when starting the Workflow. The translation service currently supports the following languages:

- `de`: German
- `es`: Spanish
- `fr`: French
- `lv`: Latvian
- `mi`: Maori
- `sk`: Slovak
- `tr`: Turkish
- `zu`: Zulu

### This is the end of the exercise.
