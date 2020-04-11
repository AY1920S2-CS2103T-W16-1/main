package seedu.address.model;

import java.util.HashMap;
import java.util.Set;

import seedu.address.model.task.Task;
import seedu.address.model.tag.Tag;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSameTask comparison)
 */
public class TagSet {

    private final HashMap<Tag, Integer> tagCount = new HashMap<>();

    public TagSet(ReadOnlyTaskList taskList) {
        tagCount.clear();
        populateTag(taskList);
    }

    public void populateTag(ReadOnlyTaskList taskList) {
        tagCount.clear();
        for (Task t: taskList.getTaskList()) {
            addTask(t);
        }
    }

    public boolean contains(Tag t) {
        return this.tagCount.containsKey(t);
    }

    public Set<Tag> getTags() {
        return tagCount.keySet();
    }

    public void addTask(Task task) {
        Set<Tag> tags = task.getTags();
        for (Tag t: tags) {
            tagCount.computeIfAbsent(t, (tag) -> 0);
            tagCount.compute(t, (tag, count) -> {
                return count + 1;
            });
        }
    }

    public void removeTask(Task task) {
        Set<Tag> tags = task.getTags();
        for (Tag t: tags) {
            tagCount.computeIfAbsent(t, (tag) -> 1); // just in case
            tagCount.compute(t, (tag, count) -> {
                if (count - 1 == 0) {
                    return null;
                }
                return count - 1;
            });
        }
    }
}
