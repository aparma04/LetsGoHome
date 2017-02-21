# YelpHome

Yelp implemetation:

Step 1 : Create your own project
Step 2 : give internet permission in manifest file
Step 2 : Put this in strings.xml file - <string name="consumerKey"></string>
					<string name="consumerSecret"></string>
					<string name="token"></string>
					<string name="tokenSecret"></string>
Step 3 : In project app gradle file put "compile 'com.yelp.clientlib:yelp-android:3.0.0'"
Step 5 : Put this in app gradle after buildTypes -
	packagingOptions {
        exclude 'META-INF/DEPENDENCIES.txt'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/NOTICE.txt'
        exclude 'META-INF/NOTICE'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/DEPENDENCIES'
        exclude 'META-INF/notice.txt'
        exclude 'META-INF/license.txt'
        exclude 'META-INF/dependencies.txt'
        exclude 'META-INF/LGPL2.1'
    }
