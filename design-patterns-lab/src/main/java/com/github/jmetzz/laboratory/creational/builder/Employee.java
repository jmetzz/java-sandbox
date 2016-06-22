package com.github.jmetzz.laboratory.creational.builder;

import com.google.common.base.MoreObjects;

public class Employee {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    private Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static Builder newEmployeeBuilder() {
        return new Builder(new Employee());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("imageUrl", imageUrl)
                .toString();
    }

    public static class Builder extends AbstractBuilder<Employee> {

        protected Builder(Employee instance) {
            super(instance);
        }

        public Builder withId(Long id) {
            getInstance().id = id;
            return this;
        }

        public Builder withName(String name) {
            getInstance().name = name;
            return this;
        }

        public Builder withImageUrl(String imageUrl) {
            getInstance().imageUrl = imageUrl;
            return this;
        }

        public Builder withDescription(String description) {
            getInstance().description = description;
            return this;
        }

        @Override
        protected void beforeBuild() {
           /* do some validation here if necessary */
        }
    }


}
