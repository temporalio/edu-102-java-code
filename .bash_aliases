alias workspace="cd ${GITPOD_REPO_ROOT}"
alias webui="gp preview $(gp url 8080)"

alias microservce="cd ${GITPOD_REPO_ROOT}/utilities/microservice && mvn exec:java -Dexec.mainClass="translationapi.Microservice""

alias ex1="cd ${GITPOD_REPO_ROOT}/exercises/durable-execution/practice"
alias ex1s="cd ${GITPOD_REPO_ROOT}/exercises/durable-execution/solution"
alias ex1w="mvn exec:java -Dexec.mainClass='translationworkflow.TranslationWorker'"
ex1st() {
    mvn exec:java -Dexec.mainClass="translationworkflow.Starter" -Dexec.args="${1}"
}

alias ex2="cd ${GITPOD_REPO_ROOT}/exercises/testing-code/practice"
alias ex2s="cd ${GITPOD_REPO_ROOT}/exercises/testing-code/solution"
alias ex2t="mvn test"


alias ex3="cd ${GITPOD_REPO_ROOT}/exercises/debug-activity/practice"
alias ex3s="cd ${GITPOD_REPO_ROOT}/exercises/debug-activity/solution"
alias ex3w="mvn exec:java -Dexec.mainClass='pizzaworkflow.PizzaWorker'"
alias ex3st="mvn exec:java -Dexec.mainClass='pizzaworkflow.Starter'"

export PATH="$PATH:/workspace/bin"
echo "temporal configured! try typing temporal -v"
echo "Your workspace is located at: ${GITPOD_REPO_ROOT}"
echo "Type the command     workspace      to return to the workspace directory at any time."