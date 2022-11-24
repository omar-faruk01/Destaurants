package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Review type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Reviews", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", provider = "userPools", operations = { ModelOperation.CREATE, ModelOperation.DELETE, ModelOperation.UPDATE }),
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.READ, ModelOperation.UPDATE }),
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.READ })
})
@Index(name = "byRestaurant", fields = {"restaurantId","comment"})
public final class Review implements Model {
  public static final QueryField ID = field("Review", "id");
  public static final QueryField RATING = field("Review", "rating");
  public static final QueryField COMMENT = field("Review", "comment");
  public static final QueryField USERNAME = field("Review", "username");
  public static final QueryField RESTAURANT_ID = field("Review", "restaurantId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Float") Double rating;
  private final @ModelField(targetType="String") String comment;
  private final @ModelField(targetType="ID") String username;
  private final @ModelField(targetType="ID", isRequired = true) String restaurantId;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public Double getRating() {
      return rating;
  }
  
  public String getComment() {
      return comment;
  }
  
  public String getUsername() {
      return username;
  }
  
  public String getRestaurantId() {
      return restaurantId;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Review(String id, Double rating, String comment, String username, String restaurantId) {
    this.id = id;
    this.rating = rating;
    this.comment = comment;
    this.username = username;
    this.restaurantId = restaurantId;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Review review = (Review) obj;
      return ObjectsCompat.equals(getId(), review.getId()) &&
              ObjectsCompat.equals(getRating(), review.getRating()) &&
              ObjectsCompat.equals(getComment(), review.getComment()) &&
              ObjectsCompat.equals(getUsername(), review.getUsername()) &&
              ObjectsCompat.equals(getRestaurantId(), review.getRestaurantId()) &&
              ObjectsCompat.equals(getCreatedAt(), review.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), review.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getRating())
      .append(getComment())
      .append(getUsername())
      .append(getRestaurantId())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Review {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("rating=" + String.valueOf(getRating()) + ", ")
      .append("comment=" + String.valueOf(getComment()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("restaurantId=" + String.valueOf(getRestaurantId()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static RestaurantIdStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Review justId(String id) {
    return new Review(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      rating,
      comment,
      username,
      restaurantId);
  }
  public interface RestaurantIdStep {
    BuildStep restaurantId(String restaurantId);
  }
  

  public interface BuildStep {
    Review build();
    BuildStep id(String id);
    BuildStep rating(Double rating);
    BuildStep comment(String comment);
    BuildStep username(String username);
  }
  

  public static class Builder implements RestaurantIdStep, BuildStep {
    private String id;
    private String restaurantId;
    private Double rating;
    private String comment;
    private String username;
    @Override
     public Review build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Review(
          id,
          rating,
          comment,
          username,
          restaurantId);
    }
    
    @Override
     public BuildStep restaurantId(String restaurantId) {
        Objects.requireNonNull(restaurantId);
        this.restaurantId = restaurantId;
        return this;
    }
    
    @Override
     public BuildStep rating(Double rating) {
        this.rating = rating;
        return this;
    }
    
    @Override
     public BuildStep comment(String comment) {
        this.comment = comment;
        return this;
    }
    
    @Override
     public BuildStep username(String username) {
        this.username = username;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, Double rating, String comment, String username, String restaurantId) {
      super.id(id);
      super.restaurantId(restaurantId)
        .rating(rating)
        .comment(comment)
        .username(username);
    }
    
    @Override
     public CopyOfBuilder restaurantId(String restaurantId) {
      return (CopyOfBuilder) super.restaurantId(restaurantId);
    }
    
    @Override
     public CopyOfBuilder rating(Double rating) {
      return (CopyOfBuilder) super.rating(rating);
    }
    
    @Override
     public CopyOfBuilder comment(String comment) {
      return (CopyOfBuilder) super.comment(comment);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
  }
  
}
