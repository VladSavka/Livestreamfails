# Livestreamfails
Android client for https://livestreamfails.com/

	              	Product and technical decisions for Mobile coding assignment  

			                        	Product core functionality

There is a list of pages with livestreamfails videos
Each page contains video itself and title 
Each page has error state if no internet connection or other errors occurs 
Video starts playing automatically when user navigates to it 
User can play or pause video

				                          Technical decisions 

Language: Kotlin
Multithreading: Coroutines 
Architecture: MVVM, Repository pattern
Network: Jsop (for parsing livestreamfails.com)

I decided to use Kotlin to reduce boilerplate code (compered with Java), Null Safety, Coroutines, and other features 

For all asynchronous operations I have used Coroutines which simplify asynchronous programming by providing possibility to write code in direct style (sequentially).
It could be used other popular tools to solve this problem such as RxJava for example. But as for me it introduce to much extra complicity to project

For general architecture I have used MVVM + ViewState pattern to make codebase easily maintainable and testable. Also as  further improvement it would be good to use “Clean Architecture” concept. It can be easily implemented by adding domain layer to the project. But currently I have decided  to not over-engineering to much in small project 

I have used Repository pattern in model layer to handle data operations. It provide a clean API so that the rest of the app can retrieve data easily.

In current project Dependency Injection was implemented manually ( see “Injection” class) to keep it simple. In production projects I would rather use Dagger 2 for DI or similar tool 

Minimum sdk version is set to 19 (Android 4.4) to cover most android devices 

List of known bugs:
Video starts playing from beginning on device rotation
While scrolling video view is empty 
On landscape mode title can be shown above the video  
There are maximum 1000 videos in list while it’s much more of them at livestreamfails.com

