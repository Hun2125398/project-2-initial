package com.csc205.project2.shapes;

/**
 * Abstract base class for three-dimensional shapes
 * Implements the ThreeDimensionalShape interface and provides common functionality
 */
public abstract class Shape3D implements ThreeDimensionalShape {
    // Private fields for encapsulation
    private String name;
    private String color;
    
    /**
     * Default constructor
     */
    public Shape3D() {
        this.name = "Unknown Shape";
        this.color = "Unknown Color";
    }
    
    /**
     * Constructor with name parameter
     * @param name The name of the shape
     */
    public Shape3D(String name) {
        this.name = name;
        this.color = "Unknown Color";
    }
    
    /**
     * Constructor with name and color parameters
     * @param name The name of the shape
     * @param color The color of the shape
     */
    public Shape3D(String name, String color) {
        this.name = name;
        this.color = color;
    }
    
    // Getter methods
    /**
     * Gets the name of the shape
     * @return The name of the shape
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the color of the shape
     * @return The color of the shape
     */
    public String getColor() {
        return color;
    }
    
    // Setter methods
    /**
     * Sets the name of the shape
     * @param name The new name for the shape
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the color of the shape
     * @param color The new color for the shape
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    // Abstract methods that subclasses must implement
    /**
     * Abstract method to calculate volume - must be implemented by subclasses
     * @return The volume of the shape
     */
    protected abstract double calculateVolume();
    
    /**
     * Abstract method to calculate surface area - must be implemented by subclasses
     * @return The surface area of the shape
     */
    protected abstract double calculateSurfaceArea();
    
    // Concrete implementations of interface methods
    /**
     * Gets the volume by calling the abstract calculateVolume method
     * @return The volume of the shape
     */
    @Override
    public double getVolume() {
        return calculateVolume();
    }
    
    /**
     * Gets the surface area by calling the abstract calculateSurfaceArea method
     * @return The surface area of the shape
     */
    @Override
    public double getSurfaceArea() {
        return calculateSurfaceArea();
    }
    
    /**
     * Returns a string representation of the shape
     * @return Formatted string with shape information
     */
    @Override
    public String toString() {
        return String.format("%s Shape [Color: %s, Volume: %.2f, Surface Area: %.2f]", 
                           name, color, getVolume(), getSurfaceArea());
    }
}