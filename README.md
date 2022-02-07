# Java Penguin Hunter
## Major project for Java Programming unit in which I scored a High Distinction mark.

Start game with **java Hunter command**

## Original Specification:

Java Penguin Hunter
Word/time limit: N/A
Weighting: 50%
Due date: 5pm AEST Sunday 15 August 2021 (Week 6)

After you have read this information, head over to the Assessment 2 Q&A discussion in Ed to ask any questions and see what your peers are saying about this assessment.

Assessment overview
This assessment is a mandatory submission, which means that in order to pass this unit of study, you must submit this assessment. This is an individual assessment task and must be your own work. You must attribute the source of any part of your code that you have not written yourself. Please note the section on plagiarism in this document.

This assessment task will require you to write a program to play the text-based game 'Java Penguin Hunter'. You will be supplied with a series of specifications that your program will need to meet.

Note: You will also be required to complete an interview with your OLA during Holiday Week 1, in which you will demonstrate your program and explain your code and design.

This assessment supports unit learning outcomes 1, 2, & 3.

Assessment overview

You will complete this assessment in the workspace environment within Ed.

In the simple Role-playing game (RPG), Java Penguin Hunter, you play as Oggy the fierce. Oggy is a penguin that swims the blue waters of the Bass Strait. Oggy’s job is to hunt the vast oceans for fresh fish each and every day. But the life of a penguin is hard, and feeding the family is not optional. Unlike the land above the water, the currency under the sea is fish. Oggy must ensure that each and every day they have enough fish to satisfy the growing chicks, with hopefully a bit left over. What is important to keep in mind is that while fish is currency, it is also food. It helps to have more fish, so you do not have to eat the very thing today that would help you survive tomorrow.

Oggy is an exceptional hunter but the schools of fish in the strait are also improving their tactics. It takes more and more each day for Oggy to be able to successfully hunt and bring home the food. But there are pitfalls that Oggy must avoid in order to keep catching the fish. But what is life without a little whimsy and chance?

If all else fails, there is always Maurice the loan shark, who is also a real shark. Maurice can help meet the daily quota if Oggy falls short, but the terms are steep and the price for not meeting the payment deadline is well… shark... penguin... I think you can guess!

The game has two basic modes of play:

Mode one allows the player to play in arcade mode (more details below), to achieve as many days as possible

Mode two allows the player to play in story mode (mode details below), to achieve the task of feeding the family for a fixed number of days (or turns)

The game ends if Oggy is not able to meet the daily quota of fish needed for the family, or if the loan term with Maurice runs out.

With this assessment you are free to choose your own design to achieve the outcome, and you may include additional aspects to make the gameplay more engaging. However, it is important to ensure that you do not miss out on any of the requirements outlined within the Gameplay specifications. Should you have questions or if there are aspects of the assessment which require further clarification, please use the Ed discussion forum for this assessment to seek help from your OLA.

Read through the following steps to complete this assessment.

Step 1: Read through the Gameplay specifications and highlight any key information. In particular take note of the details outlined in the Requirements section.

Step 2: Using the specifications document as your basis, write a program to play the game Java Penguin Hunter. Refer to the Hints and suggestions section to inform your process.

Step 3: Produce your demonstration video in which you will show your program and explain your code and design.

Gameplay
The following criteria outlines the gameplay specifications your Java Penguin Hunter program will need to meet:

When the game begins the system will read the file called ‘weapons.txt’. This file contains a list of possible weapons that Oggy is able to use to hunt for fish each day. The file contains the following details:

Weapon name – a description of the weapon

Damage - the damage the weapon causes

Strong against – the weapon is strong against the fish being hunted, leading to doubling the number of fish caught

Weak against – the weapon is weak against the fish being hunted, leading to halving the number of fish caught

Cost – the number of fish it costs to use the weapon

Min Fish – the minimum number of fish that can be caught

Max Fish – the maximum number of fish that can be caught

There are four main types of fish that Oggy hunts, which are as follows:

Sardine

Mackarel

Shrimp

Cod

When the game starts, the player playing as Oggy is prompted to enter their name, which must be between 3 and 12 characters long (both inclusive). If this criterion is not met, the player is asked to re-enter their name until correct. They may choose another name for the penguin.

The player is then asked to select which mode they would like to play in:

Arcade: the game goes on until Oggy is not able to get enough fish required for the end of the day. Highest number of days is written to the file at the end of the game.

Story mode: the player is asked to enter the number of turns they would like to play. This must be greater than or equal to 5. The player is also asked to enter a target number of the fish balance remaining after the turns. The number entered must be greater than or equal to 25. Any incorrect information must prompt the user to re-enter. The program must also validate this information.

In both play modes:

The player begins the game with 10 fish.

The game then randomly generates a number between 10–20. This number indicates the number of fish the family needs for today in order to survive.

The game then also randomly generates a second number between 1–10. This number indicates the daily predator insurance premium which applies to Oggy and his colleagues when swimming in the strait.

The game will also present the player with a 1% probability of a natural disaster happening. This will cause a random penalty of between 50 and 100 fish.

The player is presented with a menu as follows:

1: catch fish

2: borrow from the loan shark

3: Skip

Based on this information, the player then selects one of the options above to perform the action (these are detailed below)

The player can hunt a maximum of 2 times in one turn.

Catching fish:

The player is asked to select their weapon on choice from the list read from the file. Each weapon costs fish.

The program will then randomly select a type of fish which Oggy will attempt to use the weapon to catch.

For the selected weapon, the game randomly generates a number between the min fish and max fish values read from the file. This will indicate the number of fish caught.

If the weapon selected is strong against the fish being hunted, the number of fish caught is doubled. However, if the weapon selected is weak against the fish being hunted, the number of fish caught is halved.

Borrowing from the loan shark

Should Oggy not be able to catch enough fish to reach the daily quota, Oggy can choose to borrow fish from Maurice, the loan shark.

When borrowing from Maurice, the player is prompted to enter the number of fish that needs to be borrowed.

This is added to the player's total number of fish.

Maurice only gives Oggy 3 turns to pay back 1.5 times the number of fish borrowed.

The maximum number of fish which can be borrowed at any one time is 30. Once the loan reaches 100 fish, no more fish can be borrowed.

After the three turns have passed, or if the number of turns lapses in story mode, the balance is deducted from the total number of fish the player has.

Skipping a turn

Should Oggy feel that the number of fish caught the previous day is more than enough to feed the family today, Oggy can simply skip hunting that day only.

This option simply deducts the number of fish required for the day from the total number of fish the player has.

The game ends when:

Arcade mode

The number of fish available at the end of the day is negative after performing all required actions.

Story mode

The required number of turns entered by the user has passed.

The player wins if the number of fish available after the turns is close to the target number of the fish balance (within +/- 10%.), the target number set by the player at the beginning of the game.

Upon completing the game, the program will write the results to the file as follows:

Arcade mode

The type of game played, the player name, and the total number of days the family was fed

Story mode

The type of game played, the player name, the number of turns, the target number of fish balance, the final number of fish after all the turns complete, and the win or loss condition.
