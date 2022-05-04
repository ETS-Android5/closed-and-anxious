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




### SQLite database
- What it is 
- How we have used it

### Navigation
The application consist of one MainActivity three Fragments (UIRWCategories, PlaylistUI and TrackUI), which are embodded in the body of main-activity by using FragmentContainerView element as the NavhostFragment that handles the navigation actions between the Destinations - here synonymous with our Fragments.

Since we are passing Argeuments between Destinations, we are following Android's best practices use the SafeArgs library to enable safe navigation between the destinations. (See also here: https://developer.android.com/guide/navigation/navigation-pass-data).

Using the Destination and Action classes provided by the utility, we use onClick-methods associated with the different RecyclerView PlaylistHolder and TrackHolder to define the Arguments to be passed and navigate between to the next Fragments. Here, as per documentation, the NavigationController object allows the NavHost to manage the nagivation actions between the Fragments, holding all information about Destinations and Actions contained in the associated nav_graph file.

<img width="668" alt="Screenshot 2022-05-04 at 17 13 24" src="https://user-images.githubusercontent.com/71443009/166713166-360a3fbb-ddfd-4b88-8a2e-3f4ea7e8d0cd.png">

In the end we just updated the PlaylistUI by deleting the Backbutton which was no longer neede for navigation. 
