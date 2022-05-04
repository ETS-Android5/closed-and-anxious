# Closed and Anxious
Closed and Anxious is a Software Design student meditation app, loosely based on the Danish app “Åben og rolig“.
We want to provide an application that has the ability to filter, display and play short audio files with guided robot voice meditations and soundscapes.
- The list displays will be based on the RecyclerView
- Audio file information is persistently stored internal SQLite database on the device (including references to images and sound-files, not the files themselves)
- A ViewModel is used to allow Fragments access to this stored information.
- The player is losely based on the stopwatch app example. Since the MediaPlayer utility manages its own lifecycles and concurrencies, we did not have to manually implement this.
- The user interface is designed via Constraint Layout
- Navigation is implemented between our three Fragments, using a Navigation graph and argument passing for dynamic user interfaces.

## Technical concepts included

### MutableLiveData
MutableLiveData is "observable data holder class" (https://developer.android.com/topic/libraries/architecture/livedata) that makes changes in the held data objects observable by other classes.
By encapsulating a MutableLiveData object in a ViewModel-class, a single instance of the object can be shared between multiple fragments that are being managed within a shared activity.
This allows for live-updates to UI-elements that rely on data from the ViewModel, for example when one Fragment updates the shared object, another Fragment observing the same ViewModel will be notified of the change and can update its UI-elements as needed.

In "Closed and Anxious", the CatView-class is the ViewModel that manages the Fragments' access to data.
The contained MutableLiveData of type ArrayList<Playlist> is an in-app representation of the data contained in the database file (see SQLite database).
Its it holds on to the data called from a maximum of three database calls (lazy loading on opening the PlaylistUI), and ensures that all Fragments have access to the most up-to-date Version of the object.

Originally, we planned to manage the Mediaplayer through MutableLiveData, but found that the MediaPlayer-class provided by Android manages its own lifecycles in a way that makes more sense for our project (most importantly its destruction and release).
We pivoted to using the MutableLiveData for our data track, but our user interface and layout ultimately did not require the use of the observable nature of class.

One way to 'force' the use could have been as a way of UI-'error'-handling for long load-times from the database.
If one category had a particularly large set of Tracks, and the call to populate the playlist would take several seconds, we could have encapsulated this in its own stream, displayed a loading message in the UI so long as the Playlist was unpopulated, and upon update, programmatically rendered the RecyclerView.

### ConstraintLayout
ConstraintLayout is one of the layouts provided by Android, making it possible to structure the user interface of a given activity and/or fragment. The advantage of this ViewGroup object is that it makes the user interface responsive.

We have exclusively used the ConstraintLayout for structuring the user interface of each Fragment in our application.

<table align="center">
  <tr>
    <td><img src="images/CategoryUI.jpg" alt="category ui" width="200"/></td>
    <td><img src="images/PlaylistUI.jpg" alt="playlist ui" width="200"/></td>
    <td><img src="images/TrackUI.jpg" alt="track ui" width="200"/></td>
  </tr>
  <tr>
    <td><p align="center">Category UI</p></td>
    <td><p align="center">Playlist UI</p></td>
    <td><p align="center">Track UI</p></td>
  </tr>
</table>


### SQLite database
- What it is 
- How we have used it

### Navigation
The application consist of one activity and three fragments, which are embodded in the body of main-activity by using FragmentContainerView ui element. This element named container-ui was transformed into NavhostFragment what allow us to set an order of fragments displayed to the user accordingly to the actions he performed. The specification of destinations from one screen to anoter was created in the nav-graph, the resource layer of the application.

There is also part of Navigation which had to be attached into the java part of application. In our main classes which are connected explicitly to UI such as UIRWCategories, TrackUI and PlaylistUI we used the NavigationController object inside Onclick method, that allows NavHost to manage destinations of chosen fragment. NavigationController is a part of the  navigation which contains all information where the fragment or activity which is attached to should go after the specific event occur(that’s why it is attached to onClick method).
  <img width="315" alt="Screenshot 2022-05-04 at 16 49 04" src="https://user-images.githubusercontent.com/71443009/166707936-8b2b2aaf-8ee3-4298-9907-14bb1dd3a796.png">

  
