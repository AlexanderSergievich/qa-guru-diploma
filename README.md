Test automation project for [Arzamas](https://arzamas.academy/)
> - Arzamas is a Russian educational website with courses on history, literature, philosophy, arts, and humanities
Table of contents
- [Tools and technologies](#hammer_and_wrench-tools-and-technologies)
- [List of implemented tests](#bookmark_tabs-list-of-implemented-tests)
- [Running autotests from the terminal](#desktop_computer-running-autotests-from-the-terminal)
- [Parameterized build in Jenkins](#-parameterized-build-in-jenkins)
- [Allure report](#-allure-report)
- [Allure TestOps](#-allure-testops)
- [Integration with Jira](#-integration-with-jira)
- [Telegram notifications using a bot](#-telegram-notifications-using-a-bot)
- [Video example of running tests in Selenoid](#-video-example-of-running-tests-in-selenoid)

Tools and technologies

| IntelliJ IDEA | Java | Selenide | Selenoid  | Allure Report |  Allure TestOps | Gradle | JUnit5 | GitHub | Jenkins| Telegram | Jira |
|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:---------:|:-----------:|
| <a href="https://www.jetbrains.com/idea/"> <img width="90%" title="IntelliJ IDEA" src="media/logotypes/Intelij_IDEA.svg"></a> | <a href="https://www.java.com/"><img width="100%" title="Java" src="media/logotypes/Java.svg"></a> | <a href="https://selenide.org/"> <img width="90%" title="Selenide" src="media/logotypes/Selenide.svg"></a> | <a href="https://aerokube.com/selenoid/"> <img width="80%" title="Selenoid" src="media/logotypes/Selenoid.svg"></a> |<a href="https://github.com/allure-framework/allure2"> <img width="100%" title="Allure Report" src="media/logotypes/Allure_Report.svg"></a> |<a href="https://qameta.io/"> <img width="60%" title="Allure TestOps" src="media/logotypes/AllureTestOps.svg"></a> |<a href="https://gradle.org/"><img width="90%" title="Gradle" src="media/logotypes/Gradle.svg"></a> |<a href="https://junit.org/junit5/"> <img width="90%" title="JUnit5" src="media/logotypes/JUnit5.svg"></a> |<a href="https://github.com/"><img width="90%" title="GitHub" src="media/logotypes/GitHub.svg"></a> | <a href="https://jenkins.autotests.cloud/"> <img width="90%" title="Jenkins" src="media/logotypes/Jenkins.svg"></a> |<a href="https://web.telegram.org/"> <img width="80%" title="Telegram" src="media/logotypes/Telegram.svg"></a> |<a href="https://jira.autotests.cloud/"> <img width="85%" title="Jira" src="media/logotypes/Jira.svg"></a>|
- To create autotests in this project the <code>[Java](https://www.java.com/)</code> language was used.
- <code>[Gradle](https://gradle.org/)</code> was used as an automatic build system.
- Frameworks <code>[JUnit5](https://junit.org/junit5/)</code> and <code>[Selenide](https://selenide.org/)</code> for automated testing of web applications have been applied.
- Browsers were launched via <code>[Selenoid](https://aerokube.com/selenoid/)</code>.
- To run tests remotely a job was implemented in <code>[Jenkins](https://jenkins.autotests.cloud/job/019-Diploma-watermelonparadise)</code> with the creation of an <code>[Allure-report](https://jenkins.autotests.cloud/job/019-Diploma-watermelonparadise/allure)</code> and sending the results to <code>[Telegram](https://web.telegram.org/)</code> using special Telegram bot.
- Integrations with с <code>[Allure TestOps](https://allure.autotests.cloud/project/3556/dashboards)</code> and <code>[Jira](https://jira.autotests.cloud/browse/HOMEWORK-827)</code> were implemented.

List of implemented tests
#### ✓ List of realized automatic tests
- [x] Buy as a Gift flow without payment
- [x] Change password
- [x] Find course by name using courses tab
- [x] Find online university course about history of russian culture by name
- [x] Sign in user using password
- [x] Sign up user without marketing distribution agreement
- [x] Update newsletter distribution
- [x] Use search field to find course

Running autotests from the terminal

To run the test locally, you need register on the site https://arzamas.academy/ and add your credentials to the file <code>***user.properties***</code> to the resources folder (src/test/resources/properties/)
You also need to register on https://jenkins.autotests.cloud/ to be able to run job to build and execute tests remotely
If you want you can fill <code>***remote.properties***</code> to adjust test execution on your on virtual machine

Lauching All tests ***locally*** can be done using the following command from the IDE terminal:
```bash  
gradle clean ArzamasTests
```
Lauching API tests:
```bash 
gradle clean ArzamasAPI
```
Lauching E2E tests:
```bash
gradle clean ArzamasE2E
```
___
Running the following command in the IDE terminal will run the tests ***remotely*** in Selenoid taking into account the specified parameters:
```bash 
gradle clean test -DisRemoteWebDriver=true
```
If you do not specify any parameters, then the test will run with the default values that we set above.

## <img src="media/logotypes/Jenkins.svg" title="Jenkins" width="4%"/> Parameterized build in Jenkins
#### Link to job in Jenkins
Using the link below you can go to the parameterized build of the project:

<code>[Link to the job in Jenkins](https://jenkins.autotests.cloud/job/019-Diploma-watermelonparadise)</code>

#### Build options
The table below shows the build options in Jenkins, their purpose and default settings.

|                      **PARAMETER**                       | **DESCRIPTION** |                    **DEFAULT VALUE**                    |
|:--------------------------------------------------------:|:---------:|:-------------------------------------------------------:|
|                    <code>TASK</code>                     | Base URL of the site for configuration settings|          ArzamasTests, ArzamasAPI, ArzamasE2E           |
|                   <code>BROWSER</code>                   | Browser type and its version|                     Chrome, Firefox                     |
|                <code>BROWSER_SIZE</code>                 | Browser size |                  1920x1080, 1440x1000                   |
|               <code>BROWSER_VERSION</code>               | Browser size |                           100                           |
|                  <code>BASE_URL</code>                   | Browser size |                 https://arzamas.academy                 |

#### Job in Jenkins: algorithm and autotests results
1. Open the project from the link above. The screenshot shows the appearance of the project window in Jenkins.
<p align="center">
<img title="Jenkins" src="media/screenshots/Jenkins.png">
</p>

2. Select the item "Collect with parameters" on the left panel

3. If necessary, change the parameters by selecting values from the drop-down lists

4. Click "Collect" button

5. The results of running a parameterized build can be viewed in <code>Allure report</code> and <code>Allure TestOps</code>
<p align="center">
<img title="Jenkins_build" src="media/screenshots/Jenkins_build_upd.png">
</p>

## <img src="media/logotypes/Allure_Report.svg" title="Allure_Report" width="4%"/> Allure report
#### Link to Allure report
Using the link below you can go to the Allure report:

<code>[Link to the  Allure report](https://jenkins.autotests.cloud/job/019-Diploma-watermelonparadise/allure)</code>

#### Allure report: overview
The screenshot below shows the main page of the report.
<p align="center">
<img title="Allure_report_overview" src="media/screenshots/Allure_report_overview.png">
</p>

#### Allure report: test cases
On the Suites tab a standard structural representation of executed tests can be found.
<p align="center">
<img title="Allure_report_suits" src="media/screenshots/Allure_report_suits_optimal.jpg">
</p>


#### Allure report: graphs
Graphs allow you to see different statistics collected from the test data: statuses breakdown or severity and duration diagrams.
<p align="center">
<img title="Allure_report_graphs1" src="media/screenshots/Allure_report_graphs1.png">
</p>
<p align="center">
<img title="Allure_report_graphs2" src="media/screenshots/Allure_report_graphs2.png">
</p>

## <img src="media/logotypes/AllureTestOps.svg" title="Allure_TestOps" width="4%"/> Allure TestOps
#### Link to Allure TestOps
Using the link below you can go to the Allure TestOps:

<code>[Link to the  Allure TestOps](https://allure.autotests.cloud/project/3556/dashboards)</code>

#### Allure TestOps: dashboard
Standard dashboard (see the picture below) provides instant insight on status of tests you're having in your project and resides in the Dashboards section of a project.
<p align="center">
<img title="Allure_testOps_overview" src="media/screenshots/Allure_testOps_overview_mini.png">
</p>
The default dashboard contains:

1. Number of test cases and distribution per state (active, in review, outdated etc.)
2. Number of test cases and distribution between the manual tests and automated tests
3. Automation trend with the insight regarding the number of test during last 14 days
4. Information about launches and their results statuses (failed, passed)
5. Mutes trend, i.e. the number of tests excluded from statistics due to permanent failed state or flaky state.

#### Allure TestOps: test cases
Allure TestOps supports working with both – manual and automated test cases. Steps of a test case are described in the scenario.

<p align="center">
<img title="Allure_testOps_test_cases" src="media/screenshots/Allure_testOps_test_cases.png">
</p>

## <img src="media/logotypes/Jira.svg" title="Jira" width="5%"/> Integration with Jira
#### Link to Jira
Using the link below you can go to the Jira:
<code>[Link to the  Jira](https://jira.autotests.cloud/browse/HOMEWORK-827)</code>
<p align="center">
<img title="Jira" src="media/screenshots/Jira.png">
</p>

## <img src="media/logotypes/Telegram.svg" title="Telegram" width="5%"/> Telegram notifications using a bot
As a result of running autotests, a config.json file is generated in the <code>notifications/</code> folder. On the basis of this file a notification is generated that the bot sends (a diagram is drawn and the corresponding text is added).

```mermaid
flowchart LR
    A(Autotests execution) -->|1| B(json file generation)
    B -->|2| C[notification generation: diagram and text]
    subgraph Allure notifications
    C -->|3| D[sending a notification to the messenger]
    end
```

<p align="center">
<img title="Telegram" src="media/screenshots/Telegram.png">
</p>

*After the completion of the autotests for each of them screenshots, page source, browser console logs and a video recording of the test are available in the report.*

## <img src="media/logotypes/Selenoid.svg" title="Selenoid" width="5%"/> Video example of running tests in Selenoid
The test report is accompanied by a video in the Attachment section.

<p align="center">
  <img title="Selenoid Video" src="media/video/§.gif">
</p>

[Back to content](#pretzel-table-of-contents)