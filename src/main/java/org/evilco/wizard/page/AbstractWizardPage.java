package org.evilco.wizard.page;

import org.evilco.wizard.Wizard;

import javax.swing.*;
import java.awt.*;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public abstract class AbstractWizardPage extends JPanel implements IWizardPage {

	/**
	 * Stores the parent wizard instance.
	 */
	private final Wizard wizard;

	/**
	 * Constructs a new AbstractWizardPage.
	 * @param wizard
	 */
	public AbstractWizardPage (Wizard wizard) {
		super ();
		this.wizard = wizard;
	}

	/**
	 * Constructs a new AbstractWizardPage.
	 * @param wizard
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public AbstractWizardPage (Wizard wizard, LayoutManager layout, boolean isDoubleBuffered) {
		super (layout, isDoubleBuffered);
		this.wizard = wizard;
	}

	/**
	 * Constructs a new AbstractWizardPage.
	 * @param wizard
	 * @param layout
	 */
	public AbstractWizardPage (Wizard wizard, LayoutManager layout) {
		super (layout);
		this.wizard = wizard;
	}

	/**
	 * Constructs a new AbstractWizardPage.
	 * @param wizard
	 * @param isDoubleBuffered
	 */
	public AbstractWizardPage (Wizard wizard, boolean isDoubleBuffered) {
		super (isDoubleBuffered);
		this.wizard = wizard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IWizardPage getNextPage () {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wizard getWizard () {
		return this.wizard;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCancelAvailable () {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFinishAvailable () {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNextAvailable () {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPreviousAvailable () {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onEnter () {
		// nothing to do
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLeave () {
		// nothing to do
	}
}
