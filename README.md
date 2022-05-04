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
- What it is 
- How we have used it

### ConstraintLayout
- What it is 
- How we have used it

### SQLite database
- What it is 
- How we have used it

### Naigation
- What it is 
- How we have used it
