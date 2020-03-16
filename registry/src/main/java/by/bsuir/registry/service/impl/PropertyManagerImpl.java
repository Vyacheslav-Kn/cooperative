package by.bsuir.registry.service.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.impl.BaseManagerImpl;
import by.bsuir.registry.model.Property;
import by.bsuir.registry.repository.PropertyRepository;
import by.bsuir.registry.service.PropertyManager;
import com.google.common.collect.ImmutableList;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@ParametersAreNonnullByDefault
public class PropertyManagerImpl extends BaseManagerImpl<PropertyRepository, Property> implements PropertyManager {

    public PropertyManagerImpl(PropertyRepository mongoRepository, MongoOperations mongoOperations) {
        super(mongoRepository);
    }

    @Override
    public Property update(Property property) throws DataManipulateException {
        Optional<Property> originalProperty = mongoRepository.findById(property.getId());
        if (originalProperty.isPresent()) {
            Collection<Property.PropertyOwner> commonOwners = intersect(originalProperty.get().getOwners(), property.getOwners());
            Collection<Property.PropertyOwner> newOwners = new ArrayList<>(property.getOwners()) {{
                removeAll(commonOwners);
            }};
            long endDate = newOwners.iterator().hasNext() ? newOwners.iterator().next().getStartDate() : TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            Property updatedProperty = new Property(
                    originalProperty.get().getId(),
                    property.getInventoryNumber(),
                    property.getSquare(),
                    new HashSet<>() {{
                        addAll(commonOwners);
                        addAll(property.getOwners());
                    }},
                    new ArrayList<>() {{
                        addAll(originalProperty.get().getHistoryOwners());
                        addAll(
                                new ArrayList<>(originalProperty.get().getOwners()) {{
                                    removeAll(commonOwners);
                                }}.stream()
                                        .map(propertyOwner -> new Property.HistoryPropertyOwner(
                                                propertyOwner.getPersonId(),
                                                propertyOwner.getStartDate(),
                                                propertyOwner.getOwningPercent(),
                                                propertyOwner.getNumberAuthority(),
                                                endDate
                                        ))
                                        .collect(Collectors.toList())
                        );
                    }},
                    property.getPtn()
            );
            return mongoRepository.save(updatedProperty);
        }
        throw new DataManipulateException();
    }

    @Override
    public Collection<Property> findAllWithOwners() {
        return mongoRepository.findAllWithOwners();
    }

    private static <T> Collection<T> intersect(Collection<T> collection1, Collection<T> collection2) {
        ImmutableList.Builder<T> builder = ImmutableList.builder();
        for (T t : collection1) {
            if (collection2.contains(t)) {
                builder.add(t);
            }
        }
        return builder.build();
    }

}
