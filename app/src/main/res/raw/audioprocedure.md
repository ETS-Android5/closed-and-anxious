# Content addition procedure #

### Create the correct media file

Create an audio file.
If it is in .wav or other formats, use an online converter to convert the file to .mp3
Re-name it to a single, lowercase word.

ex: hello.mp3 or pray.mp3

### Checkout the branch "morecontent"

git checkout morecontent
git pull --all

### Save in raw directory

Move the file to this path:
app/src/main/res/raw

Please allow Android Studio to add the files to the Git if you are prompted.

### Write content information

Needed information:
Meditation-Name
Meditation-Description
Category ("Noise", "Anxiety", "Frustration")

### Choose a cover image 

Choose an image from the folder:
app/src/main/res/drawable

### Add to the DB

Go to the Class TracksDB.
Find the method "fillItemsDB".
Find the section of the categotry of your Track.
Following the pattern already there (line 54 ff.), call the addTrack-method.
As arguments, pass:

addTrack(R.raw.audiofilename, "Track Name", "Track Description", "Category", R.drawable.coverfilename);

### Repeat for your other files

### Testing

Delete the app from your phone.
Re-install the app.
Navigate to the categories you added files in and check if they show up, and if they play as intended.

### Fixes

If this did not work as intended, de-install again, and run the gradle build command before the run command.
Inspect the Logcat of the project as you click around - and see if you can spot a print statement that tells you what's wrong.

### Commit your additions
git commit -am "more audiofiles"
git push
