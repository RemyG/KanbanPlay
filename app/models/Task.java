package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Task extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1595597376553109611L;

	@Id
	public Long id;

	@Required
	public String label;

	public String description;

	@ManyToOne
	@Required
	public Section section;

	public static Finder<Long, Task> find = new Finder<Long, Task>(Long.class,
			Task.class);

	public static List<Task> all() {
		return find.all();
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public void updateSection(Section section) {
		Section oldSection = this.section;
		oldSection.tasks.remove(this);
		oldSection.update();
		this.section = section;
		section.tasks.add(this);
		section.update();
		this.update();
	}
}
