package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
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

/** This is an auto generated class representing the Restaurant type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Restaurants", authRules = {
  @AuthRule(allow = AuthStrategy.OWNER, ownerField = "owner", identityClaim = "cognito:username", provider = "userPools", operations = { ModelOperation.CREATE, ModelOperation.DELETE, ModelOperation.UPDATE }),
  @AuthRule(allow = AuthStrategy.PRIVATE, operations = { ModelOperation.READ, ModelOperation.UPDATE }),
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.READ })
})
public final class Restaurant implements Model {
  public static final QueryField ID = field("Restaurant", "id");
  public static final QueryField NAME = field("Restaurant", "name");
  public static final QueryField ADDRESS = field("Restaurant", "address");
  public static final QueryField CITY = field("Restaurant", "city");
  public static final QueryField STATE = field("Restaurant", "state");
  public static final QueryField ZIP_CODE = field("Restaurant", "zipCode");
  public static final QueryField COUNTRY = field("Restaurant", "country");
  public static final QueryField PICTURE1 = field("Restaurant", "picture1");
  public static final QueryField PICTURE2 = field("Restaurant", "picture2");
  public static final QueryField USERNAME = field("Restaurant", "username");
  public static final QueryField RESTRICTIONS = field("Restaurant", "restrictions");
  public static final QueryField CATEGORY = field("Restaurant", "category");
  public static final QueryField RATING = field("Restaurant", "rating");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String name;
  private final @ModelField(targetType="String") String address;
  private final @ModelField(targetType="String") String city;
  private final @ModelField(targetType="String") String state;
  private final @ModelField(targetType="String") String zipCode;
  private final @ModelField(targetType="String") String country;
  private final @ModelField(targetType="String") String picture1;
  private final @ModelField(targetType="String") String picture2;
  private final @ModelField(targetType="ID") String username;
  private final @ModelField(targetType="String") List<String> restrictions;
  private final @ModelField(targetType="String") String category;
  private final @ModelField(targetType="Float") Double rating;
  private final @ModelField(targetType="Review") @HasMany(associatedWith = "restaurantId", type = Review.class) List<Review> review = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getAddress() {
      return address;
  }
  
  public String getCity() {
      return city;
  }
  
  public String getState() {
      return state;
  }
  
  public String getZipCode() {
      return zipCode;
  }
  
  public String getCountry() {
      return country;
  }
  
  public String getPicture1() {
      return picture1;
  }
  
  public String getPicture2() {
      return picture2;
  }
  
  public String getUsername() {
      return username;
  }
  
  public List<String> getRestrictions() {
      return restrictions;
  }
  
  public String getCategory() {
      return category;
  }
  
  public Double getRating() {
      return rating;
  }
  
  public List<Review> getReview() {
      return review;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Restaurant(String id, String name, String address, String city, String state, String zipCode, String country, String picture1, String picture2, String username, List<String> restrictions, String category, Double rating) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
    this.picture1 = picture1;
    this.picture2 = picture2;
    this.username = username;
    this.restrictions = restrictions;
    this.category = category;
    this.rating = rating;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Restaurant restaurant = (Restaurant) obj;
      return ObjectsCompat.equals(getId(), restaurant.getId()) &&
              ObjectsCompat.equals(getName(), restaurant.getName()) &&
              ObjectsCompat.equals(getAddress(), restaurant.getAddress()) &&
              ObjectsCompat.equals(getCity(), restaurant.getCity()) &&
              ObjectsCompat.equals(getState(), restaurant.getState()) &&
              ObjectsCompat.equals(getZipCode(), restaurant.getZipCode()) &&
              ObjectsCompat.equals(getCountry(), restaurant.getCountry()) &&
              ObjectsCompat.equals(getPicture1(), restaurant.getPicture1()) &&
              ObjectsCompat.equals(getPicture2(), restaurant.getPicture2()) &&
              ObjectsCompat.equals(getUsername(), restaurant.getUsername()) &&
              ObjectsCompat.equals(getRestrictions(), restaurant.getRestrictions()) &&
              ObjectsCompat.equals(getCategory(), restaurant.getCategory()) &&
              ObjectsCompat.equals(getRating(), restaurant.getRating()) &&
              ObjectsCompat.equals(getCreatedAt(), restaurant.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), restaurant.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getAddress())
      .append(getCity())
      .append(getState())
      .append(getZipCode())
      .append(getCountry())
      .append(getPicture1())
      .append(getPicture2())
      .append(getUsername())
      .append(getRestrictions())
      .append(getCategory())
      .append(getRating())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Restaurant {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("address=" + String.valueOf(getAddress()) + ", ")
      .append("city=" + String.valueOf(getCity()) + ", ")
      .append("state=" + String.valueOf(getState()) + ", ")
      .append("zipCode=" + String.valueOf(getZipCode()) + ", ")
      .append("country=" + String.valueOf(getCountry()) + ", ")
      .append("picture1=" + String.valueOf(getPicture1()) + ", ")
      .append("picture2=" + String.valueOf(getPicture2()) + ", ")
      .append("username=" + String.valueOf(getUsername()) + ", ")
      .append("restrictions=" + String.valueOf(getRestrictions()) + ", ")
      .append("category=" + String.valueOf(getCategory()) + ", ")
      .append("rating=" + String.valueOf(getRating()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static Restaurant justId(String id) {
    return new Restaurant(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      address,
      city,
      state,
      zipCode,
      country,
      picture1,
      picture2,
      username,
      restrictions,
      category,
      rating);
  }
  public interface BuildStep {
    Restaurant build();
    BuildStep id(String id);
    BuildStep name(String name);
    BuildStep address(String address);
    BuildStep city(String city);
    BuildStep state(String state);
    BuildStep zipCode(String zipCode);
    BuildStep country(String country);
    BuildStep picture1(String picture1);
    BuildStep picture2(String picture2);
    BuildStep username(String username);
    BuildStep restrictions(List<String> restrictions);
    BuildStep category(String category);
    BuildStep rating(Double rating);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String picture1;
    private String picture2;
    private String username;
    private List<String> restrictions;
    private String category;
    private Double rating;
    @Override
     public Restaurant build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Restaurant(
          id,
          name,
          address,
          city,
          state,
          zipCode,
          country,
          picture1,
          picture2,
          username,
          restrictions,
          category,
          rating);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep address(String address) {
        this.address = address;
        return this;
    }
    
    @Override
     public BuildStep city(String city) {
        this.city = city;
        return this;
    }
    
    @Override
     public BuildStep state(String state) {
        this.state = state;
        return this;
    }
    
    @Override
     public BuildStep zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }
    
    @Override
     public BuildStep country(String country) {
        this.country = country;
        return this;
    }
    
    @Override
     public BuildStep picture1(String picture1) {
        this.picture1 = picture1;
        return this;
    }
    
    @Override
     public BuildStep picture2(String picture2) {
        this.picture2 = picture2;
        return this;
    }
    
    @Override
     public BuildStep username(String username) {
        this.username = username;
        return this;
    }
    
    @Override
     public BuildStep restrictions(List<String> restrictions) {
        this.restrictions = restrictions;
        return this;
    }
    
    @Override
     public BuildStep category(String category) {
        this.category = category;
        return this;
    }
    
    @Override
     public BuildStep rating(Double rating) {
        this.rating = rating;
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
    private CopyOfBuilder(String id, String name, String address, String city, String state, String zipCode, String country, String picture1, String picture2, String username, List<String> restrictions, String category, Double rating) {
      super.id(id);
      super.name(name)
        .address(address)
        .city(city)
        .state(state)
        .zipCode(zipCode)
        .country(country)
        .picture1(picture1)
        .picture2(picture2)
        .username(username)
        .restrictions(restrictions)
        .category(category)
        .rating(rating);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder address(String address) {
      return (CopyOfBuilder) super.address(address);
    }
    
    @Override
     public CopyOfBuilder city(String city) {
      return (CopyOfBuilder) super.city(city);
    }
    
    @Override
     public CopyOfBuilder state(String state) {
      return (CopyOfBuilder) super.state(state);
    }
    
    @Override
     public CopyOfBuilder zipCode(String zipCode) {
      return (CopyOfBuilder) super.zipCode(zipCode);
    }
    
    @Override
     public CopyOfBuilder country(String country) {
      return (CopyOfBuilder) super.country(country);
    }
    
    @Override
     public CopyOfBuilder picture1(String picture1) {
      return (CopyOfBuilder) super.picture1(picture1);
    }
    
    @Override
     public CopyOfBuilder picture2(String picture2) {
      return (CopyOfBuilder) super.picture2(picture2);
    }
    
    @Override
     public CopyOfBuilder username(String username) {
      return (CopyOfBuilder) super.username(username);
    }
    
    @Override
     public CopyOfBuilder restrictions(List<String> restrictions) {
      return (CopyOfBuilder) super.restrictions(restrictions);
    }
    
    @Override
     public CopyOfBuilder category(String category) {
      return (CopyOfBuilder) super.category(category);
    }
    
    @Override
     public CopyOfBuilder rating(Double rating) {
      return (CopyOfBuilder) super.rating(rating);
    }
  }
  
}
