package org.evilco.wizard.demo;

import org.evilco.wizard.Wizard;
import org.evilco.wizard.demo.page.WelcomePage;

import javax.swing.*;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class DemoWizard {

	/**
	 * Main Entry Point.
	 * @param arguments
	 */
	public static void main (String[] arguments) throws Exception {
		// load system look and feel
		UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());

		// create wizard instance
		Wizard wizard = new Wizard ("Demo Wizard");

		// initialize
		wizard.initialize (new WelcomePage (wizard));
	}
}
