package org.evilco.wizard.demo.page;

import org.evilco.wizard.Wizard;
import org.evilco.wizard.page.AbstractWizardPage;
import org.evilco.wizard.page.IWizardPage;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class WelcomePage extends AbstractWizardPage {

	/**
	 * Stores the next page.
	 */
	protected final IWizardPage nextPage;

	/**
	 * Constructs a new WelcomePage.
	 * @param wizard
	 */
	public WelcomePage (Wizard wizard) {
		super (wizard);

		this.setLayout (new FlowLayout (FlowLayout.LEFT));
		this.add (new Label ("Welcome to the demo wizard!"));

		// create next page
		this.nextPage = new DynamicPage (wizard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IWizardPage getNextPage () {
		return this.nextPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCancelAvailable () {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNextAvailable () {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnter () {
		super.onEnter ();

		System.out.println ("Entered welcome page.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLeave () {
		super.onLeave ();

		System.out.println ("Left welcome page.");
	}
}
