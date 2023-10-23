package edu.wpi.first.wpilib.versioning;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;

public class WPILibVersioningPluginExtension {
    // Version and time are in properties, in order to make them easily passable to other properties
    // and easily configurable
    private final Property<String> version;
    private final Property<String> time;
    private boolean useAllTags = false;
    private boolean buildServerMode = false;
    private boolean releaseMode = false;
    private List<String> matchGlobs = new ArrayList<>();

    public WPILibVersioningPluginExtension(Project project, WPILibVersionProvider versionProvider) {
        version = project.getObjects().property(String.class);
        time = project.getObjects().property(String.class);

        LocalDateTime.now();
        time.set(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        version.set(project.provider(() -> versionProvider.getVersion(this, project, this.useAllTags, this.matchGlobs)));
    }

    public Property<String> getTime() {
        return time;
    }

    public Property<String> getVersion() {
        return version;
    }

    public boolean isBuildServerMode() {
        return buildServerMode;
    }

    public void setBuildServerMode(boolean mode) {
        buildServerMode = mode;
    }

    public boolean isReleaseMode() {
        return releaseMode;
    }

    public void setReleaseMode(boolean mode) {
        releaseMode = mode;
    }

    public boolean isUseAllTags() {
        return useAllTags;
    }

    public void setUseAllTags(boolean allTags) {
        useAllTags = allTags;
    }

    public void setMatchGlobs(List<String> globList) {
        matchGlobs = globList;
    }

    public void addMatchGlob(String glob) {
        matchGlobs.add(glob);
    }
}
