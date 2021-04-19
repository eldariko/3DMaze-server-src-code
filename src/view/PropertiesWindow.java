package view;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import boot.RunServer;
import boot.ServerProperties;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;

public class PropertiesWindow {
	ServerProperties sp;
	Shell shlServerProperties;
	public void open() {
		Display display = Display.getDefault();
		 shlServerProperties = new Shell();
		shlServerProperties.setModified(true);
		shlServerProperties.setSize(205, 168);
		shlServerProperties.setText("server properties");
		shlServerProperties.setLayout(new FormLayout());
		
		Composite serverComp = new Composite(shlServerProperties, SWT.NONE);
		FormData fd_serverComp = new FormData();
		fd_serverComp.bottom = new FormAttachment(0, 129);
		fd_serverComp.right = new FormAttachment(0, 189);
		fd_serverComp.top = new FormAttachment(0);
		fd_serverComp.left = new FormAttachment(0);
		serverComp.setLayoutData(fd_serverComp);
		serverComp.setLayout(new GridLayout(3, false));
		
		Label lblNumberOfClients = new Label(serverComp, SWT.NONE);
		lblNumberOfClients.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblNumberOfClients.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.BOLD));
		lblNumberOfClients.setText("Number of clients :");
		
		Spinner spNumOfClients = new Spinner(serverComp, SWT.BORDER);
		spNumOfClients.setTouchEnabled(true);
		spNumOfClients.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.BOLD));
		spNumOfClients.setMaximum(30);
		spNumOfClients.setMinimum(1);
		spNumOfClients.setSelection(10);
		
		Label lblNewLabel = new Label(serverComp, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.BOLD));
		lblNewLabel.setText("Port :");
		
		Spinner spPort = new Spinner(serverComp, SWT.BORDER);
		spPort.setFont(SWTResourceManager.getFont("Comic Sans MS", 9, SWT.BOLD));
		spPort.setMaximum(8000);
		spPort.setMinimum(1024);
		spPort.setSelection(4000);
		new Label(serverComp, SWT.NONE);
		new Label(serverComp, SWT.NONE);
		
		Button btnOk = new Button(serverComp, SWT.NONE);
		btnOk.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 sp=new ServerProperties();
				 sp.setServerPort(spPort.getSelection());
				sp.setNumOfClients(spNumOfClients.getSelection());
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream("server properties.xml");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				XMLEncoder xmlEncoder = new XMLEncoder(bos);
				xmlEncoder.writeObject(sp);
				xmlEncoder.close();
				new RunServer().run(sp);
				shlServerProperties.close();
				
			}
		});
		btnOk.setFont(SWTResourceManager.getFont("Comic Sans MS", 10, SWT.BOLD));
		btnOk.setText("Ok");
		new Label(serverComp, SWT.NONE);
		shlServerProperties.open();
		shlServerProperties.layout();
		while (!shlServerProperties.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
