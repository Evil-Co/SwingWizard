package org.evilco.wizard;

import org.evilco.wizard.component.GenericWizardContainer;
import org.evilco.wizard.component.IWizardContainer;
import org.evilco.wizard.page.IWizardPage;

import java.awt.*;
import java.util.Stack;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class Wizard {

	/**
	 * Stores the current wizard page.
	 */
	protected IWizardPage currentPage = null;

	/**
	 * Stores the stack of wizard pages.
	 */
	protected Stack<IWizardPage> pageStack = new Stack<IWizardPage> ();

	/**
	 * Stores the wizard container.
	 */
	protected IWizardContainer container = null;

	/**
	 * Constructs a new Wizard.
	 * @param container
	 */
	public Wizard (IWizardContainer container) {
		this.container = container;
		this.container.setWizard (this);
	}

	/**
	 * Constructs a new Wizard.
	 * @param title
	 */
	public Wizard (String title) {
		this.container = new GenericWizardContainer (title);
		this.container.setWizard (this);
	}

	/**
	 * Returns the wizard container.
	 * @return
	 */
	public IWizardContainer getContainer () {
		return this.container;
	}

	/**
	 * Initializes the wizard.
	 * @param page
	 */
	public void initialize (IWizardPage page) {
		// clear stack
		this.pageStack.clear ();

		// set new page
		this.setPage (page, false);

		// call event callback
		this.container.onInitialize ();
	}

	/**
	 * Switches to the next page.
	 */
	public void nextPage () {
		// cancel if no next page is available
		if (!this.currentPage.isNextAvailable ()) return;

		// get next page
		IWizardPage newPage = this.currentPage.getNextPage ();

		// set new page
		this.setPage (newPage, true);
	}

	/**
	 * Internal callback for wizard cancellation.
	 */
	public void onCancel () {
		this.currentPage.onLeave ();
	}

	/**
	 * Internal callback for wizard finishing.
	 */
	public void onFinish () {
		this.currentPage.onLeave ();
	}

	/**
	 * Switches back to the previous page.
	 */
	public void previousPage () {
		// cancel if no previous page is available
		if (!this.currentPage.isPreviousAvailable ()) return;

		// request button update if nothing is available in our stack
		if (this.pageStack.empty ()) {
			this.updateButtons ();
			return;
		}

		// get previous page
		IWizardPage page = this.pageStack.pop ();

		// set new page
		this.setPage (page, false);
	}

	/**
	 * Sets a new page.
	 * @param page
	 */
	public void setPage (IWizardPage page, boolean append) {
		if (this.currentPage != null) {
			// notify page
			this.currentPage.onLeave ();

			// add page to stack
			if (append) this.pageStack.push (this.currentPage);

			// remove element
			this.container.getWizardPageContainer ().remove (((Component) this.currentPage));
		}

		// set new page
		this.currentPage = page;

		// notify new page
		this.currentPage.onEnter ();

		// append to container
		this.container.getWizardPageContainer ().add (((Component) this.currentPage));
		this.container.getWizardPageContainer ().validate ();
		this.container.getWizardPageContainer ().repaint ();

		// update buttons
		this.updateButtons ();
	}

	/**
	 * Updates the state of all buttons.
	 */
	public void updateButtons () {
		this.container.setCancelAvailable ((this.currentPage.isCancelAvailable () && !this.currentPage.isFinishAvailable ()));
		this.container.setFinishAvailable (this.currentPage.isFinishAvailable ());
		this.container.setNextAvailable (this.currentPage.isNextAvailable ());
		this.container.setPreviousAvailable ((!this.pageStack.empty () && this.currentPage.isPreviousAvailable ()));
	}
}
