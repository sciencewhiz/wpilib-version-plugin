package edu.wpi.first.wpilib.versioning;

import java.util.List;

import org.gradle.api.Project;

// Interface to make passing a groovy function to a java API possible
// without completely loosing intellisense.
public interface WPILibVersionProvider {
    String getVersion(WPILibVersioningPluginExtension extension, Project project, boolean allTags, List<String> matchGlobs);
}
