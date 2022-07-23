# CS476-Project
![cover](https://user-images.githubusercontent.com/79466152/123284782-c1cec280-d4c9-11eb-83e8-469be191d81b.png)

<h4 align="center">This Android application will feature an easy way for the user to track their golf scores. It is designed to track up to four players per round of golf. This Android application intends to help users focus on having fun with the game while tracking their score at ease.</h4>

<p align="center">
  <a href="#key-features">Key features</a> •
  <a href="#installation">Installation</a> •
  <a href="#user-manual">User manual</a> •
  <a href="#troubleshooting-tips">Troubleshooting tips</a> •
  <a href="#bug-list">Bug list</a> •
  <a href="#planned-features">Planned features</a> •
  <a href="#license">License</a> •
  <a href="#credits">Credits</a> •
  <a href="#contact-info">Contact Info</a>
</p>

## Key Features

Add new player.

![new player interface](https://user-images.githubusercontent.com/79466152/123832602-beb94500-d8c2-11eb-9468-fac0092fa897.PNG)


Create new round.

![new round](https://user-images.githubusercontent.com/79466152/123832672-d395d880-d8c2-11eb-81be-7346c1980232.PNG)


Track golf score.

![score keeping](https://user-images.githubusercontent.com/79466152/123833459-9716ac80-d8c3-11eb-9901-920fbac47361.PNG)


## Installation

You can install Mulligan Marker by downloading the [APK](https://github.com/laanh200/CS476-Project/releases/tag/1.0.1) from GitHub and install it.

*This will require Android Studio already installed on your machine.

## User manual
To add a new player.

![new option](https://user-images.githubusercontent.com/79466152/123833755-e957cd80-d8c3-11eb-94fe-81cc6c143b40.png)
![new menu - new player](https://user-images.githubusercontent.com/79466152/123833766-ed83eb00-d8c3-11eb-959c-89b7f3a30f20.png)
![new player - save](https://user-images.githubusercontent.com/79466152/123833770-ef4dae80-d8c3-11eb-9dee-a167e0931f17.PNG)


To add new round.

![new menu - new round](https://user-images.githubusercontent.com/79466152/123833831-012f5180-d8c4-11eb-8b84-055ffcc106f3.png)
![new round - course](https://user-images.githubusercontent.com/79466152/123833853-08eef600-d8c4-11eb-8630-a42c6a5ce679.png)
![course list](https://user-images.githubusercontent.com/79466152/123833861-0ab8b980-d8c4-11eb-9fc3-50be9abb6ffa.PNG)
![new round - date](https://user-images.githubusercontent.com/79466152/123833880-0db3aa00-d8c4-11eb-88fb-dc5088c9d4a8.png)
![date](https://user-images.githubusercontent.com/79466152/123833884-0ee4d700-d8c4-11eb-9b68-e31253ae6139.PNG)
![new round - save](https://user-images.githubusercontent.com/79466152/123833899-11473100-d8c4-11eb-9947-0983714a8b1f.png)


To add player and their teebox into the round.

![select player](https://user-images.githubusercontent.com/79466152/123834013-3176f000-d8c4-11eb-9cb1-804c2bc652d1.PNG)
![pick player](https://user-images.githubusercontent.com/79466152/123835392-b31b4d80-d8c5-11eb-9ec4-e5fd2aea5a15.PNG)
![new scorecard confirm](https://user-images.githubusercontent.com/79466152/123835424-bdd5e280-d8c5-11eb-8cd6-29db93029edc.PNG)


To access the current list of players or rounds.

*Swipe from left to right.

![home screen - swipe](https://user-images.githubusercontent.com/79466152/123834338-8f0b3c80-d8c4-11eb-94bf-5c583d1e6d08.png)
![navigation drawer](https://user-images.githubusercontent.com/79466152/123834345-929ec380-d8c4-11eb-9e5d-03564efcef19.PNG)


To delele player.

![player list - swipe to delete](https://user-images.githubusercontent.com/79466152/123837795-80bf1f80-d8c8-11eb-9a7e-b468385b53d6.PNG)
![player list - delete player](https://user-images.githubusercontent.com/79466152/123837807-83ba1000-d8c8-11eb-9bb6-5af0a8ca7584.PNG)

To delete round.

![round list swipe](https://user-images.githubusercontent.com/79466152/123838069-caa80580-d8c8-11eb-9e97-af80e9661549.PNG)
![round list delete round](https://user-images.githubusercontent.com/79466152/123837869-92a0c280-d8c8-11eb-9433-1a2df5931d19.PNG)

To access the round.

- Touch the target round.

![select the round](https://user-images.githubusercontent.com/79466152/123838152-e6131080-d8c8-11eb-96e2-481466b648f5.PNG)


To track golf score.

![score keeping - select hole score](https://user-images.githubusercontent.com/79466152/123834416-a34f3980-d8c4-11eb-8ef8-9be651e5c5fe.PNG)
![score picker](https://user-images.githubusercontent.com/79466152/123834428-a6e2c080-d8c4-11eb-9932-4edcf51a4fd7.PNG)
![show score on scorecard](https://user-images.githubusercontent.com/79466152/123834470-af3afb80-d8c4-11eb-97b2-85ba7f8f0d39.PNG)
![score keeping - finished](https://user-images.githubusercontent.com/79466152/123834572-cb3e9d00-d8c4-11eb-9acb-8eb675283741.PNG)


## Troubleshooting tips

Restart the application.

## Bug list
Course and Date pickers come up on first click, but not clickable until the focus changes from the EditTextInput. 

## Planned features
*	The ability to restore deleted player.
*	Run advertisement at the bottom.
*	The ability to export the scorecard into CSV.
*	The ability to email or text scorecard to the player.
* Handicap calculation.
* Statistics screen.
* Notifications to link to the rounds in progress. 


## License

All rights are reserved to [@laanh200]( https://github.com/laanh200) and [@jasonn66](https://github.com/jasonn66). Nobody else can change it. The only rights granted are the rights to look at it and fork the project to look at it in their own account. 

## Credits
This application was created from the following packages:
-	[Room Database](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
-	[Androidx Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
-	[Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
-	[Reference Room Database video](https://www.youtube.com/watch?v=lwAvI3WDXBY)
-	[Reference Navigation Drawer video](https://www.youtube.com/watch?v=do4vb0MdLFY)
-	[View Binding Property Delegate](https://github.com/kirich1409/ViewBindingPropertyDelegate)
-	[PowerSpinner](https://github.com/skydoves/PowerSpinner)

## Contact Info
>GitHub [@laanh200]( https://github.com/laanh200)

>GitHub [@jasonn66](https://github.com/jasonn66)
