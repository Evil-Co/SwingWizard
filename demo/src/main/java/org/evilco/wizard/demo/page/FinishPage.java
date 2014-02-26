package org.evilco.wizard.demo.page;

import org.evilco.wizard.Wizard;
import org.evilco.wizard.page.AbstractWizardPage;

import javax.swing.*;
import java.awt.*;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class FinishPage extends AbstractWizardPage {

	/**
	 * Constructs a new FinishPage.
	 * @param wizard
	 */
	public FinishPage (Wizard wizard) {
		super (wizard);

		this.setLayout (new FlowLayout (FlowLayout.LEFT));
		this.add (new JLabel ("Thanks for using the demo wizard!"));
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
	public boolean isFinishAvailable () {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnter () {
		super.onEnter ();

		System.out.println ("Entered finish page.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLeave () {
		super.onLeave ();

		System.out.println ("Left finish page.");
	}
}
