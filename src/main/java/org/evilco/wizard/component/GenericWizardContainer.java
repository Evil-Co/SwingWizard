package org.evilco.wizard.component;

import org.evilco.wizard.Wizard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

/**
 * @auhtor Johannes Donath <johannesd@evil-co.com>
 * @copyright Copyright (C) 2014 Evil-Co <http://www.evil-co.org>
 */
public class GenericWizardContainer extends JFrame implements IWizardContainer {

	/**
	 * Defines the default minimal size.
	 */
	public static final Dimension DEFAULT_MINIMUM_SIZE = new Dimension(500, 500);

	/**
	 * Defines the next button text.
	 */
	public static final String STRING_NEXT = "Next";

	/**
	 * Defines the finish button text.
	 */
	public static final String STRING_FINISH = "Finish";

	/**
	 * Stores the wizard button panel.
	 */
	protected JPanel buttonPanel = new JPanel (new FlowLayout (FlowLayout.TRAILING));

	/**
	 * Stores the wizard container.
	 */
	protected JPanel container = new JPanel (new GridLayout (1, 1));

	/**
	 * Stores the cancel button.
	 */
	protected JButton cancelButton = new JButton ("Cancel");

	/**
	 * Indicates whether the next button has been transformed into a finish button.
	 */
	protected boolean isFinishAvailable = false;

	/**
	 * Stores the next button.
	 */
	protected JButton nextButton = new JButton (STRING_NEXT);

	/**
	 * Stores the previous button.
	 */
	protected JButton previousButton = new JButton ("Previous");

	/**
	 * Stores the wizard instance.
	 */
	protected Wizard wizard = null;

	/**
	 * Constructs a new GenericWizardContainer.
	 * @throws HeadlessException
	 */
	public GenericWizardContainer () throws HeadlessException {
		super ();

		this.buildUI ();
		this.hookEvents ();
	}

	/**
	 * Constructs a new GenericWizardContainer.
	 * @param gc
	 */
	public GenericWizardContainer (GraphicsConfiguration gc) {
		super (gc);

		this.buildUI ();
		this.hookEvents ();
	}

	/**
	 * Constructs a new GenericWizardContainer.
	 * @param title
	 * @throws HeadlessException
	 */
	public GenericWizardContainer (String title) throws HeadlessException {
		super (title);

		this.buildUI ();
		this.hookEvents ();
	}

	/**
	 * Constructs a new GenericWizardContainer.
	 * @param title
	 * @param gc
	 */
	public GenericWizardContainer (String title, GraphicsConfiguration gc) {
		super (title, gc);

		this.buildUI ();
		this.hookEvents ();
	}

	/**
	 * Constructs the UI.
	 */
	protected void buildUI () {
		// set minimal size
		this.setMinimumSize (DEFAULT_MINIMUM_SIZE);

		// center
		this.setLocationRelativeTo (null);

		// set close operation
		this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

		// set layout
		this.setLayout (new BorderLayout ());

		// wire elements together
		this.add (this.container, BorderLayout.CENTER);
		this.add (this.buttonPanel, BorderLayout.PAGE_END);

		// add decoration
		this.buttonPanel.setBorder (new MatteBorder (1, 0, 0, 0, Color.LIGHT_GRAY));

		this.buttonPanel.add (this.previousButton);
		this.buttonPanel.add (this.nextButton);
		this.buttonPanel.add (this.cancelButton);
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
	public Container getWizardPageContainer () {
		return this.container;
	}

	/**
	 * Hooks all events.
	 */
	protected void hookEvents () {
		this.cancelButton.addActionListener (new ActionListener () {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void actionPerformed (ActionEvent e) {
				wizard.onCancel ();
				dispose ();
			}
		});

		this.nextButton.addActionListener (new ActionListener () {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void actionPerformed (ActionEvent e) {
				if (isFinishAvailable) {
					wizard.onFinish ();
					dispose ();
				} else
					wizard.nextPage ();
			}
		});

		this.previousButton.addActionListener (new ActionListener () {

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void actionPerformed (ActionEvent e) {
				wizard.previousPage ();
			}
		});

		this.cancelButton.addContainerListener (new DynamicContainerSizeListener (this));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onInitialize () {
		this.setVisible (true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCancelAvailable (boolean b) {
		this.cancelButton.setEnabled (b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFinishAvailable (boolean b) {
		this.isFinishAvailable = b;

		if (b)
			this.nextButton.setText (STRING_FINISH);
		else
			this.nextButton.setText (STRING_NEXT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNextAvailable (boolean b) {
		this.nextButton.setEnabled (b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPreviousAvailable (boolean b) {
		this.previousButton.setEnabled (b);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWizard (Wizard wizard) {
		this.wizard = wizard;
	}

	/**
	 * A utility class which automatically adjusts the container size.
	 */
	public static class DynamicContainerSizeListener implements ContainerListener {

		/**
		 * Stores the parent frame.
		 */
		protected final JFrame frame;

		/**
		 * Constructs a new DynamicContainerSizeListener.
		 * @param frame
		 */
		public DynamicContainerSizeListener (JFrame frame) {
			this.frame = frame;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void componentAdded (ContainerEvent e) {
			// get window dimensions
			Dimension windowSize = this.frame.getSize ();
			Dimension windowPreferredSize = this.frame.getPreferredSize ();

			// create new dimension
			Dimension newSize = new Dimension ();
			newSize.width = ((int) Math.max (windowPreferredSize.getWidth (), windowSize.getWidth ()));
			newSize.height = ((int) Math.max (windowPreferredSize.getHeight (), windowSize.getHeight ()));

			// update size
			this.frame.setMinimumSize (newSize);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void componentRemoved (ContainerEvent e) { }
	}
}
