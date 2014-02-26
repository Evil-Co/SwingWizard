package org.evilco.wizard.demo.page;

import org.evilco.wizard.Wizard;
import org.evilco.wizard.page.AbstractWizardPage;
import org.evilco.wizard.page.IWizardPage;

import javax.swing.*;
import java.awt.*;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class DynamicPage extends AbstractWizardPage {

	/**
	 * Stores the amount of times the user entered the page.
	 */
	protected int enterCount = 0;

	/**
	 * Stores the next page.
	 */
	protected final IWizardPage nextPage;

	/**
	 * Stores the page text.
	 */
	protected JLabel pageText = new JLabel ("Not initialized ...");

	/**
	 * Constructs a new DynamicPage.
	 * @param wizard
	 */
	public DynamicPage (Wizard wizard) {
		super (wizard);

		this.setLayout (new FlowLayout (FlowLayout.LEFT));
		this.add (this.pageText);

		this.nextPage = new FinishPage (wizard);
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
	public boolean isNextAvailable () {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnter () {
		super.onEnter ();

		System.out.println ("Entered dynamic page.");

		this.enterCount++;
		this.pageText.setText ("You entered this page " + this.enterCount + " times.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLeave () {
		super.onLeave ();

		System.out.println ("Left dynamic page.");
	}
}
