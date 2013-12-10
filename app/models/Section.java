package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Section extends Model {

	@Id
	public Long id;

	@Required
	public String label;
	
	@OneToMany
	public List<Task> tasks;

	public static Finder<Long, Section> find = new Finder(Long.class, Section.class);

	public static List<Section> all() {
		return find.all();
	}

	public static void create(Section section) {
		section.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
		task.update();
		this.update();
	}
	
	public static Map<String,String> options() {
        List<Section> sections = find.all();
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Section section: sections) {
            options.put(section.id.toString(), section.label);
        }
        return options;
    }
	
}
