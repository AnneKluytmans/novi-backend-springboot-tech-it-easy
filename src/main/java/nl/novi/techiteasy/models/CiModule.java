package nl.novi.techiteasy.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class CiModule {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Type cannot be empty.")
    @Size(max = 24, message = "type cannot exceed 24 characters.")
    private String type;

    @NotBlank(message = "Brand cannot be empty.")
    @Size(max = 50, message = "Brand name cannot exceed 50 characters.")
    private String brand;

    @NotBlank(message = "Name cannot be empty.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    private String name;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
    private Double price;

    @NotNull(message = "Original stock is required.")
    @Min(value = 0, message = "Original stock cannot be negative.")
    private Integer originalStock;

    @NotNull(message = "Sold is required.")
    @Min(value = 0, message = "Sold items cannot be negative.")
    private Integer sold;

    @PastOrPresent(message = "Sale Date cannot be in the future.")
    private LocalDate saleDate;

    @NotBlank(message = "Provider is required.")
    @Size(max = 100, message = "Provide cannot exceed 100 characters.")
    private String provider;

    @NotBlank(message = "Encoding type is required")
    @Size(max = 24, message = "Encoding type cannot exceed 24 characters.")
    private String encodingType;


    //Constructors
    public CiModule() {
    }

    public CiModule(Long id, String type, String brand, String name, Double price, Integer originalStock, Integer sold, LocalDate saleDate, String provider, String encodingType) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.originalStock = originalStock;
        this.sold = sold;
        this.saleDate = saleDate;
        this.provider = provider;
        this.encodingType = encodingType;
    }

    //Getters en setters

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (type.length() > 24) {
            throw new IllegalArgumentException("Brand name cannot exceed 50 characters.");
        }
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (brand.length() > 50) {
            throw new IllegalArgumentException("Brand name cannot exceed 50 characters.");
        }
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        if (name.length() > 100) {
            throw new IllegalArgumentException("Brand name cannot exceed 100 characters.");
        }
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price == null || price <= 0.0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        if (originalStock == null || originalStock < 0) {
            throw new IllegalArgumentException("Original stock cannot be negative.");
        }
        this.originalStock = originalStock;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        if (saleDate != null && saleDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Sale date cannot be in the future.");
        }
        this.saleDate = saleDate;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        if (provider == null || provider.isBlank()) {
            throw new IllegalArgumentException("Provider cannot be empty.");
        }
        if (provider.length() > 100) {
            throw new IllegalArgumentException("Provider name cannot exceed 100 characters.");
        }
        this.provider = provider;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        if (encodingType == null || encodingType.isBlank()) {
            throw new IllegalArgumentException("EncodingType cannot be empty.");
        }
        if (encodingType.length() > 24) {
            throw new IllegalArgumentException("EncodingType cannot exceed 24 characters.");
        }
        this.encodingType = encodingType;
    }
}
