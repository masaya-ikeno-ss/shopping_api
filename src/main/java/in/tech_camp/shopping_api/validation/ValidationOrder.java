package in.tech_camp.shopping_api.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({ ValidationPriority1.class, ValidationPriority2.class, ValidationPriority3.class })
public interface ValidationOrder {

}
