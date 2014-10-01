Swing Wizard [![Build Status](http://assets.evil-co.com/build/JSW-MASTER.png)](http://www.evil-co.com/ci/browse/JSW)
===============
The Swing Wizard library allows you to easily create dependency free wizards for
your Swing/AWT based applications.

Customize every single page or even the whole wizard window easily and enjoy the
fully pre-configured page management.

Usage
-----
To get started with this wizard library you will have to create one or more pages
which implement the interface `IWizardPage` or extend the abstract class `AbstractWizardPage`
(a simple example is available [here](demo/src/main/java/org/evilco/wizard/demo/page/WelcomePage.java).

To initialize the wizard you will additionally have to create an instance of `Wizard`
(which can either be initialized with a wizard title (for the default layout) or
a custom container implementation). The wizard can then be started by calling the
`initialize` function with a reference to the first page.

The follow-up pages are defined by the currently active page (via the `getNextPage` method).
To dynamically update the buttons after accessing the page the method `updateButtons`
in the `Wizard` class can be used.

__Please Note:__ Pages should never directly initialize page instances in the `getNextPage`
method. This method will be called each time te next button is clicked (even after
the page has already been accessed before (e.g. if the user used the previous button).

__Please Note:__ All implementations have to extend the `Component` class of AWT
to work correctly. Page instances passed to the `Wizard` class which do not extend
this class will cause an exception to occur upon access. This is a small design issue
of this library but should not cause any problems if the library is being used
the right way. The use of the abstract classes provided is recommended.

__Please Note:__ This library can easily be used in your maven projects by using the
following dependency and repository (the library might become available via Maven
Central in the future):

	<!-- ... -->
	<repository>
		<id>basket</id>
		<url>http://basket.cindyscats.com/content/repositories/snapshots/</url>
		<snapshots>
			<enabled>true</enabled>
		</snapshots>
	</repository>
	<!-- ... -->

	<!-- ... -->
	<dependency>
		<groupId>org.evilco</groupId>
		<artifactId>wizard</artifactId>
		<version>1.0.0-SNAPSHOT</version>
	</dependency>
	<!-- ... -->

Compiling
---------

You need to have Maven installed (http://maven.apache.org). Once installed,
simply run:

	mvn clean install

Maven will automatically download dependencies for you. Note: For that to work,
be sure to add Maven to your "PATH".

Issue tracker
------------

The issue tracker for this project is available at https://evilco.atlassian.net/browse/JSW/.

Contributing
------------

We happily accept contributions. The best way to do this is to fork SwingWizard
on GitHub, add your changes, and then submit a pull request. We'll look at it,
make comments, and merge it into SwingWizard if everything works out.

By submitting code, you agree to place to license your code under the
[Apache 2.0 License](LICENSE).
