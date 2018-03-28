======= WHAT IS THIS ===============================================================================

    This project is created like a template to start another project. It`s based on MVP pattern and
    Onion architecture, besides use Dagger2 to injection dependencies, RX2 to reactive programming
    and Retrofit2 as http layer. Other libraries used in the project:

    - timber - Library to print logs
    - butterknife - Library to inject views.
    - glide - Library to load images.
    - lombok - Plugin that generates getters and setters for POJO classes.
    - parceler - Library to parcel any object between activities.
    - crashlytics - Library to register any crash in app.
    - calligraphyVersion - Library to use custom fonts.
    - threeTen - Library to use dates
    - modelmapper - Library to map any object to another.

======= STEPS TO CONFIGURE PROJECT =================================================================

    1. Rename androidApplicationId and testApplicationId in build.gradle.
        com.omvp.app -> com.companyname.projectname

    2. Register application in Firebase and download|replace google-services.json contained in
    app module. Remember that project has n(flavours) different configurations, dev and prod.
    Therefore you must register n(flavours) applications instead of one.
        {androidApplicationId}
        {androidApplicationId}.dev
        {androidApplicationId}.mock

    3. Install lombok plugin in your AndroidStudio.

    4. Create|Replace release.jks keystore
        buildSystem/release.jks

    5. Copy and rename local.properties.sample to local.properties, any property defined in this
    file is available through BuildConfig.KEY_TO_USE in DEBUG configuration. Use this file to
    custom properties like user and password credentials used only in development mode.

    6. Define what languages will use the application in
    com.omvp.app.injector.module.LocaleModule

    7. Defines path to store pictures in device in: (Replace package com.omvp.app by yours)
        + dev/res/xml/provider_paths.xml
        + prod/res/xml/provider_paths.xml

    ==== TO USE CRASHLYTICS ========================================================================

        1. Configure app/fabric.properties. To personalize configuration like distribution or
        release notes, modify preferences in build.gradle.

    ==== TO USE ANALYTICS ==========================================================================

        1. Put analytics code in: (More info in TrackManager.java)
            + dev/res/xml/app_tracker.xml
            + prod/res/xml/app_tracker.xml

    ==== TO USE URBANAIRSHIP ==========================================================================

        1. Register application in urbanairship, remember to create one application by flavour.
            {androidApplicationId}
            {androidApplicationId}.dev
            {androidApplicationId}.mock

        2. Configure urbanairship.properties in:
            + mock/assets/airshipconfig.properties
            + dev/assets/airshipconfig.properties
            + prod/assets/airshipconfig.properties

======= TIPS =======================================================================================

    1. All project configuration is located in build.gradle
    2. Library dependency configuration is located in buildSystem/dependencies.gradle.
    3. keystore's is located in buildSystem/*

======= STEPS TO DELIVERY PROJECT ==================================================================

    1. Remove every sample class or file from project.
        + AndroidManifest.xml
        + com.omvp.app.ui.samples
        + res/layouts/sample*
        + app/build.gradle -> Remove any reference to res/layouts/sample*
        + Sample*.java
    2. Remove any permission (androidManifest.xml) that is not necessary for the project.
    3. Remove any dependencies that is not necessary for the project.
    4. Remove any zombie class due to the elimination of dependencies. (For example, if we remove
    urbanairship dependency, we must remove clases or functionalities related with UrbanAirship).
