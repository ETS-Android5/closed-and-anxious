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

### ConstraintLayout
ConstraintLayout is one of the layouts provided by Android, making it possible to structure the user interface of a given activity and/or fragment. The advantage of this ViewGroup object is that it makes the user interface respoinsive.

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

### Naigation
- What it is 
- How we have used it
