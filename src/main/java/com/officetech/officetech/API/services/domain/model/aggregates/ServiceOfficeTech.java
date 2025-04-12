package com.officetech.officetech.API.services.domain.model.aggregates;

import com.officetech.officetech.API.services.domain.model.commands.CreateNewServiceOfficeTechCommand;
import com.officetech.officetech.API.services.domain.model.valueobjects.*;
import com.officetech.officetech.API.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) // it is used to automatically populate the createdAt and updatedAt fields
public class ServiceOfficeTech extends AuditableAbstractAggregateRoot<ServiceOfficeTech> {

    private String title;
    private String description;
    private Float estimatePricing;
    private String date;
    private Status statusValue;
    @Setter
    private String status;
    @Setter
    private String comment;
    private Long companyId;
    private Long technicianId;
    @Setter
    private Integer rating;

    public ServiceOfficeTech() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.estimatePricing = (float) 0;
        this.date = Strings.EMPTY;
        this.comment = Strings.EMPTY;
        this.status = "In Progress";
        this.statusValue = Status.IN_PROGRESS;
        this.companyId = 0L;
        this.technicianId = 0L;
        this.rating = 0;
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public ServiceOfficeTech(CreateNewServiceOfficeTechCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.estimatePricing = command.estimatePricing();
        this.date = command.date();
        this.status = "In Progress";
        this.statusValue = Status.IN_PROGRESS;
        this.comment = command.comment();
        this.companyId = command.companyId();
        this.technicianId = command.technicianId();
        this.rating = command.rating();
        this.setCreatedAt();
        this.setUpdatedAt();
    }

    public boolean isCompleted() {
        return this.statusValue == Status.COMPLETED;
    }
    public boolean isInProgress() {
        return this.statusValue == Status.IN_PROGRESS;
    }
    public boolean isCancelled() {
        return this.statusValue == Status.CANCELLED;
    }
    public boolean isActive() {
        return this.statusValue == Status.ACTIVE;
    }
    public void setStatusValue(String status) {
        this.status = status;
        this.statusValue = Status.valueOf(status.toUpperCase().replace(" ", "_"));
    }
}
