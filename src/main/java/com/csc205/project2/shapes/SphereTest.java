package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for Sphere
 * Tests basic functionality, calculation accuracy, boundary conditions,
 * input validation, and inheritance behavior
 */
@DisplayName("Sphere Tests")
class SphereTest {
    
    private static final double DELTA = 0.001; // Tolerance for floating point comparisons
    
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        @Test
        @DisplayName("Default constructor creates sphere with default values")
        void defaultConstructor() {
            Sphere sphere = new Sphere();
            
            assertEquals("Sphere", sphere.getName());
            assertEquals("Unknown Color", sphere.getColor());
            assertEquals(1.0, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Constructor with radius sets correct values")
        void constructorWithRadius() {
            double radius = 5.0;
            Sphere sphere = new Sphere(radius);
            
            assertEquals("Sphere", sphere.getName());
            assertEquals("Unknown Color", sphere.getColor());
            assertEquals(radius, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Full constructor sets all values correctly")
        void fullConstructor() {
            String name = "Test Sphere";
            String color = "Red";
            double radius = 3.5;
            
            Sphere sphere = new Sphere(name, color, radius);
            
            assertEquals(name, sphere.getName());
            assertEquals(color, sphere.getColor());
            assertEquals(radius, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Radius getter and setter work correctly")
        void radiusGetterSetter() {
            Sphere sphere = new Sphere();
            double newRadius = 7.5;
            
            sphere.setRadius(newRadius);
            assertEquals(newRadius, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Diameter calculation is correct")
        void diameterCalculation() {
            double radius = 4.0;
            Sphere sphere = new Sphere(radius);
            
            assertEquals(2 * radius, sphere.getDiameter(), DELTA);
        }
        
        @Test
        @DisplayName("toString method returns properly formatted string")
        void toStringFormatting() {
            Sphere sphere = new Sphere("Test Sphere", "Blue", 2.0);
            String result = sphere.toString();
            
            assertTrue(result.contains("Test Sphere"));
            assertTrue(result.contains("Blue"));
            assertTrue(result.contains("2.00"));
        }
    }
    
    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationAccuracyTests {
        
        @Test
        @DisplayName("Volume calculation for radius 3 is accurate")
        void sphereVolumeCalculation() {
            // Volume of sphere with radius 3 should be 4/3 * π * 3³ = 113.097...
            double radius = 3.0;
            Sphere sphere = new Sphere(radius);
            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
            
            assertEquals(expectedVolume, sphere.getVolume(), DELTA);
            assertEquals(113.097, sphere.getVolume(), 0.01); // Known mathematical result
        }
        
        @Test
        @DisplayName("Surface area calculation for radius 2 is accurate")
        void sphereSurfaceAreaCalculation() {
            // Surface area of sphere with radius 2 should be 4π * 2² = 50.265...
            double radius = 2.0;
            Sphere sphere = new Sphere(radius);
            double expectedSurfaceArea = 4 * Math.PI * Math.pow(radius, 2);
            
            assertEquals(expectedSurfaceArea, sphere.getSurfaceArea(), DELTA);
            assertEquals(50.265, sphere.getSurfaceArea(), 0.01); // Known mathematical result
        }
        
        @Test
        @DisplayName("Unit sphere calculations are correct")
        void unitSphereCalculations() {
            // Unit sphere (radius = 1) has volume = 4π/3 and surface area = 4π
            Sphere unitSphere = new Sphere(1.0);
            
            assertEquals(4 * Math.PI / 3, unitSphere.getVolume(), DELTA);
            assertEquals(4 * Math.PI, unitSphere.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Large radius calculations are accurate")
        void largeRadiusCalculations() {
            double radius = 100.0;
            Sphere sphere = new Sphere(radius);
            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
            double expectedSurfaceArea = 4 * Math.PI * Math.pow(radius, 2);
            
            assertEquals(expectedVolume, sphere.getVolume(), 0.1);
            assertEquals(expectedSurfaceArea, sphere.getSurfaceArea(), 0.1);
        }
    }
    
    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {
        
        @Test
        @DisplayName("Very small positive radius works correctly")
        void verySmallRadius() {
            double smallRadius = 0.001;
            Sphere sphere = new Sphere(smallRadius);
            
            assertTrue(sphere.getVolume() > 0);
            assertTrue(sphere.getSurfaceArea() > 0);
            assertEquals(smallRadius, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Large radius works correctly")
        void largeRadius() {
            double largeRadius = 1000000.0;
            Sphere sphere = new Sphere(largeRadius);
            
            assertTrue(sphere.getVolume() > 0);
            assertTrue(sphere.getSurfaceArea() > 0);
            assertEquals(largeRadius, sphere.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Fractional radius works correctly")
        void fractionalRadius() {
            double fractionalRadius = 0.5;
            Sphere sphere = new Sphere(fractionalRadius);
            
            assertEquals(fractionalRadius, sphere.getRadius(), DELTA);
            assertTrue(sphere.getVolume() > 0);
            assertTrue(sphere.getSurfaceArea() > 0);
        }
    }
    
    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {
        
        @Test
        @DisplayName("Constructor with zero radius throws exception")
        void constructorWithZeroRadius() {
            // Decision: Zero radius should throw IllegalArgumentException
            // because a sphere with zero radius is geometrically meaningless
            assertThrows(IllegalArgumentException.class, () -> {
                new Sphere(0.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative radius throws exception")
        void constructorWithNegativeRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Sphere(-5.0);
            });
        }
        
        @Test
        @DisplayName("Setter with zero radius throws exception")
        void setterWithZeroRadius() {
            Sphere sphere = new Sphere(1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                sphere.setRadius(0.0);
            });
        }
        
        @Test
        @DisplayName("Setter with negative radius throws exception")
        void setterWithNegativeRadius() {
            Sphere sphere = new Sphere(1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                sphere.setRadius(-2.5);
            });
        }
        
        @Test
        @DisplayName("Full constructor with invalid radius throws exception")
        void fullConstructorWithInvalidRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Sphere("Test", "Red", -1.0);
            });
        }
        
        @Test
        @DisplayName("Exception messages are descriptive")
        void exceptionMessagesAreDescriptive() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Sphere(0.0);
            });
            
            assertTrue(exception.getMessage().contains("positive"));
            assertTrue(exception.getMessage().contains("greater than zero"));
        }
    }
    
    @Nested
    @DisplayName("Inheritance Testing")
    class InheritanceTests {
        
        @Test
        @DisplayName("Sphere works as Shape3D reference")
        void sphereAsShape3D() {
            // Test polymorphic behavior
            Shape3D shape = new Sphere("Poly Sphere", "Green", 4.0);
            
            assertEquals("Poly Sphere", shape.getName());
            assertEquals("Green", shape.getColor());
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Test that calculations work through base class interface
            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(4.0, 3);
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Sphere implements ThreeDimensionalShape interface")
        void sphereImplementsInterface() {
            ThreeDimensionalShape shape = new Sphere(3.0);
            
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Verify interface methods work correctly
            double expectedVolume = (4.0 / 3.0) * Math.PI * Math.pow(3.0, 3);
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Sphere toString works through inheritance")
        void toStringThroughInheritance() {
            Shape3D shape = new Sphere("Inherited", "Yellow", 1.5);
            String result = shape.toString();
            
            assertTrue(result.contains("Inherited"));
            assertTrue(result.contains("Yellow"));
            assertTrue(result.contains("1.50"));
        }
        
        @Test
        @DisplayName("Inherited name and color properties work correctly")
        void inheritedPropertiesWork() {
            Sphere sphere = new Sphere("Test Name", "Test Color", 2.0);
            
            // Test inherited setters
            sphere.setName("New Name");
            sphere.setColor("New Color");
            
            assertEquals("New Name", sphere.getName());
            assertEquals("New Color", sphere.getColor());
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Multiple spheres have independent properties")
        void independentSpheres() {
            Sphere sphere1 = new Sphere("Sphere1", "Red", 1.0);
            Sphere sphere2 = new Sphere("Sphere2", "Blue", 2.0);
            
            assertNotEquals(sphere1.getRadius(), sphere2.getRadius());
            assertNotEquals(sphere1.getName(), sphere2.getName());
            assertNotEquals(sphere1.getColor(), sphere2.getColor());
            assertNotEquals(sphere1.getVolume(), sphere2.getVolume());
        }
        
        @Test
        @DisplayName("Sphere calculations remain consistent after property changes")
        void calculationsAfterPropertyChanges() {
            Sphere sphere = new Sphere(1.0);
            double originalVolume = sphere.getVolume();
            
            sphere.setName("Changed Name");
            sphere.setColor("Changed Color");
            
            // Volume should remain the same since radius didn't change
            assertEquals(originalVolume, sphere.getVolume(), DELTA);
            
            // Now change radius and verify volume changes
            sphere.setRadius(2.0);
            assertNotEquals(originalVolume, sphere.getVolume(), DELTA);
        }
    }
}