package com.routine.www.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="summary")
public class Summary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="understanding")
	private int understanding;
	
	@Column(name="folder")
	private String folder;
	
	
	
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="summary_id")
	private Collection<Summary> related = new LinkedHashSet<Summary>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="summary_id")
	private Collection<Question> questions = new LinkedHashSet<Question>();
	
	@CreationTimestamp
	private Timestamp createdate;
	
	@UpdateTimestamp
	private Timestamp updatedate;
	
	

	public Summary() {
		
	}
	
	
	public Summary(String title, String content, int understanding, String folder) {
		this.title = title;
		this.content = content;
		this.understanding = understanding;
		this.folder = folder;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUnderstanding() {
		return understanding;
	}

	public void setUnderstanding(int understanding) {
		this.understanding = understanding;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Collection<Summary> getRelated() {
		return related;
	}

	public void setRelated(List<Summary> related) {
		this.related = related;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Timestamp getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	public Timestamp getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}
	
	public void addSummary(Summary summary) {
		this.related.add(summary);
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}


	@Override
	public String toString() {
		return "Summary [id=" + id + ", title=" + title + ", content=" + content + ", understanding=" + understanding
				+ ", folder=" + folder + ", related=" + related + ", questions=" + questions + ", createdate="
				+ createdate + ", updatedate=" + updatedate + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Summary other = (Summary) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
