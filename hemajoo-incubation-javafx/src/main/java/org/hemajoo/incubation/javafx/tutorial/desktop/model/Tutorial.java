/*
 * Copyright(c) 2018 - Hemajoo Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Hemajoo Incubation Software (HIS) project
 * which is licensed under the Apache license version 2 and use is subject to
 * license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.hemajoo.incubation.javafx.tutorial.desktop.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model for the tutorial JavaFX desktop application.
 * <hr>
 * @author  Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public class Tutorial
{
	/**
	 * Tutorial name.
	 */
	private StringProperty name;

	/**
	 * Tutorial description.
	 */
	private StringProperty description;

	/**
	 * Tutorial index.
	 */
	private IntegerProperty index;

	/**
	 * Tutorial author name.
	 */
	private StringProperty author;

	/**
	 * Tutorial domain.
	 */
	private StringProperty domain;

	/**
	 * Tutorial main class full path name.
	 */
	private StringProperty fullPathname;

	/**
	 * Tutorial creation date.
	 */
	private ObjectProperty<LocalDate> created;

	public Tutorial()
	{
		this.name = new SimpleStringProperty("");
		this.description = new SimpleStringProperty("");
		this.author = new SimpleStringProperty("");
		this.index = new SimpleIntegerProperty(0);
		this.domain = new SimpleStringProperty("");
		this.fullPathname = new SimpleStringProperty("");
		LocalDate today = LocalDate.now();
		this.created = new SimpleObjectProperty<>(today);
	}

	/**
	 * 
	 * @param name
	 * @param description
	 */
	public Tutorial(String name, String description)
	{
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);

		// Some initial dummy data, just for convenient testing.
		this.author = new SimpleStringProperty("Resse Christophe");
		this.index = new SimpleIntegerProperty(1234);
		this.domain = new SimpleStringProperty("Akka");
		this.created = new SimpleObjectProperty<>(LocalDate.of(2018, 1, 1));
		this.fullPathname = new SimpleStringProperty("/src/org/.../Tutorial1");
	}

	public String getName()
	{
		return name.get();
	}

	public void setName(String name)
	{
		this.name.set(name);
	}

	public StringProperty getPropertyName()
	{
		return name;
	}

	public String getDescription()
	{
		return description.get();
	}

	public void setDescription(String description)
	{
		this.description.set(description);
	}

	public StringProperty getPropertyDescription()
	{
		return description;
	}

	public String getAuthor()
	{
		return author.get();
	}

	public void setAuthor(String author)
	{
		this.author.set(author);
	}

	public StringProperty getPropertyAuthor()
	{
		return author;
	}

	public String getDomain()
	{
		return domain.get();
	}

	public void setDomain(String domain)
	{
		this.domain.set(domain);
	}

	public StringProperty getPropertyDomain()
	{
		return domain;
	}

	public int getIndex()
	{
		return index.get();
	}

	public void setIndex(int index)
	{
		this.index.set(index);
	}

	public IntegerProperty getPropertyIndex()
	{
		return index;
	}

	public LocalDate getCreated()
	{
		return created.get();
	}

	public void setCreated(LocalDate created)
	{
		this.created.set(created);
	}

	public ObjectProperty<LocalDate> getPropertyCreated()
	{
		return created;
	}

	public String getFullPathname()
	{
		return fullPathname.get();
	}

	public void setFullPathname(String path)
	{
		this.fullPathname.set(path);
	}

	public StringProperty getPropertyFullPathname()
	{
		return fullPathname;
	}
}
