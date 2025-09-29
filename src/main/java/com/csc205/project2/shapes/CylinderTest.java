package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for Cylinder
 * Tests basic functionality, calculation accuracy, boundary conditions,
 * input validation, and inheritance behavior
 */
@DisplayName("Cylinder Tests")
class CylinderTest {
    
    private static final double DELTA = 0.001; // Tolerance for floating point comparisons
    
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        @Test
        @DisplayName("Default constructor creates cylinder with default values")
        void defaultConstructor() {
            Cylinder cylinder = new Cylinder();
            
            assertEquals("Cylinder", cylinder.getName());
            assertEquals("Unknown Color", cylinder.getColor());
            assertEquals(1.0, cylinder.getRadius(), DELTA);
            assertEquals(1.0, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Constructor with radius and height sets correct values")
        void constructorWithDimensions() {
            double radius = 3.0;
            double height = 5.0;
            Cylinder cylinder = new Cylinder(radius, height);
            
            assertEquals("Cylinder", cylinder.getName());
            assertEquals("Unknown Color", cylinder.getColor());
            assertEquals(radius, cylinder.getRadius(), DELTA);
            assertEquals(height, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Full constructor sets all values correctly")
        void fullConstructor() {
            String name = "Test Cylinder";
            String color = "Red";
            double radius = 2.5;
            double height = 4.0;
            
            Cylinder cylinder = new Cylinder(name, color, radius, height);
            
            assertEquals(name, cylinder.getName());
            assertEquals(color, cylinder.getColor());
            assertEquals(radius, cylinder.getRadius(), DELTA);
            assertEquals(height, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Radius getter and setter work correctly")
        void radiusGetterSetter() {
            Cylinder cylinder = new Cylinder();
            double newRadius = 3.5;
            
            cylinder.setRadius(newRadius);
            assertEquals(newRadius, cylinder.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Height getter and setter work correctly")
        void heightGetterSetter() {
            Cylinder cylinder = new Cylinder();
            double newHeight = 6.5;
            
            cylinder.setHeight(newHeight);
            assertEquals(newHeight, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Diameter calculation is correct")
        void diameterCalculation() {
            double radius = 4.0;
            Cylinder cylinder = new Cylinder(radius, 5.0);
            
            assertEquals(2 * radius, cylinder.getDiameter(), DELTA);
        }
        
        @Test
        @DisplayName("Base area calculation is correct")
        void baseAreaCalculation() {
            double radius = 3.0;
            Cylinder cylinder = new Cylinder(radius, 2.0);
            double expectedBaseArea = Math.PI * Math.pow(radius, 2);
            
            assertEquals(expectedBaseArea, cylinder.getBaseArea(), DELTA);
        }
        
        @Test
        @DisplayName("Lateral surface area calculation is correct")
        void lateralSurfaceAreaCalculation() {
            double radius = 2.0;
            double height = 4.0;
            Cylinder cylinder = new Cylinder(radius, height);
            double expectedLateralArea = 2 * Math.PI * radius * height;
            
            assertEquals(expectedLateralArea, cylinder.getLateralSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("toString method returns properly formatted string")
        void toStringFormatting() {
            Cylinder cylinder = new Cylinder("Test Cylinder", "Blue", 2.0, 3.0);
            String result = cylinder.toString();
            
            assertTrue(result.contains("Test Cylinder"));
            assertTrue(result.contains("Blue"));
            assertTrue(result.contains("2.00"));
            assertTrue(result.contains("3.00"));
        }
    }
    
    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationAccuracyTests {
        
        @Test
        @DisplayName("Volume calculation for radius 2, height 3 is accurate")
        void cylinderVolumeCalculation() {
            // Volume of cylinder with radius 2, height 3 should be π * 2² * 3 = 12π ≈ 37.699
            double radius = 2.0;
            double height = 3.0;
            Cylinder cylinder = new Cylinder(radius, height);
            double expectedVolume = Math.PI * Math.pow(radius, 2) * height;
            
            assertEquals(expectedVolume, cylinder.getVolume(), DELTA);
            assertEquals(37.699, cylinder.getVolume(), 0.01);
        }
        
        @Test
        @DisplayName("Surface area calculation for radius 1, height 2 is accurate")
        void cylinderSurfaceAreaCalculation() {
            // Surface area = 2πr² + 2πrh = 2π(1)² + 2π(1)(2) = 2π + 4π = 6π ≈ 18.849
            double radius = 1.0;
            double height = 2.0;
            Cylinder cylinder = new Cylinder(radius, height);
            double expectedSurfaceArea = 2 * Math.PI * Math.pow(radius, 2) + 2 * Math.PI * radius * height;
            
            assertEquals(expectedSurfaceArea, cylinder.getSurfaceArea(), DELTA);
            assertEquals(18.849, cylinder.getSurfaceArea(), 0.01);
        }
        
        @Test
        @DisplayName("Unit cylinder calculations are correct")
        void unitCylinderCalculations() {
            // Unit cylinder (radius = 1, height = 1) has volume = π and surface area = 4π
            Cylinder unitCylinder = new Cylinder(1.0, 1.0);
            
            assertEquals(Math.PI, unitCylinder.getVolume(), DELTA);
            assertEquals(4 * Math.PI, unitCylinder.getSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Tall cylinder calculations are accurate")
        void tallCylinderCalculations() {
            double radius = 1.0;
            double height = 10.0;
            Cylinder cylinder = new Cylinder(radius, height);
            
            assertEquals(Math.PI * 10, cylinder.getVolume(), DELTA); // π * 1² * 10
            assertEquals(22 * Math.PI, cylinder.getSurfaceArea(), DELTA); // 2π + 20π
        }
        
        @Test
        @DisplayName("Wide cylinder calculations are accurate")
        void wideCylinderCalculations() {
            double radius = 5.0;
            double height = 1.0;
            Cylinder cylinder = new Cylinder(radius, height);
            
            assertEquals(25 * Math.PI, cylinder.getVolume(), DELTA); // π * 5² * 1
            assertEquals(60 * Math.PI, cylinder.getSurfaceArea(), DELTA); // 2π(25) + 2π(5)(1)
        }
    }
    
    @Nested
    @DisplayName("Boundary Testing")
    class BoundaryTests {
        
        @Test
        @DisplayName("Very small positive dimensions work correctly")
        void verySmallDimensions() {
            double smallRadius = 0.001;
            double smallHeight = 0.001;
            Cylinder cylinder = new Cylinder(smallRadius, smallHeight);
            
            assertTrue(cylinder.getVolume() > 0);
            assertTrue(cylinder.getSurfaceArea() > 0);
            assertEquals(smallRadius, cylinder.getRadius(), DELTA);
            assertEquals(smallHeight, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Large dimensions work correctly")
        void largeDimensions() {
            double largeRadius = 1000.0;
            double largeHeight = 1000.0;
            Cylinder cylinder = new Cylinder(largeRadius, largeHeight);
            
            assertTrue(cylinder.getVolume() > 0);
            assertTrue(cylinder.getSurfaceArea() > 0);
            assertEquals(largeRadius, cylinder.getRadius(), DELTA);
            assertEquals(largeHeight, cylinder.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Extreme aspect ratios work correctly")
        void extremeAspectRatios() {
            // Very tall, thin cylinder
            Cylinder tallThin = new Cylinder(0.1, 100.0);
            assertTrue(tallThin.getVolume() > 0);
            assertTrue(tallThin.getSurfaceArea() > 0);
            
            // Very short, wide cylinder
            Cylinder shortWide = new Cylinder(100.0, 0.1);
            assertTrue(shortWide.getVolume() > 0);
            assertTrue(shortWide.getSurfaceArea() > 0);
        }
    }
    
    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {
        
        @Test
        @DisplayName("Constructor with zero radius throws exception")
        void constructorWithZeroRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(0.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with zero height throws exception")
        void constructorWithZeroHeight() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(1.0, 0.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative radius throws exception")
        void constructorWithNegativeRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(-2.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative height throws exception")
        void constructorWithNegativeHeight() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(1.0, -3.0);
            });
        }
        
        @Test
        @DisplayName("Radius setter with invalid values throws exception")
        void radiusSetterValidation() {
            Cylinder cylinder = new Cylinder(1.0, 1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cylinder.setRadius(0.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                cylinder.setRadius(-1.5);
            });
        }
        
        @Test
        @DisplayName("Height setter with invalid values throws exception")
        void heightSetterValidation() {
            Cylinder cylinder = new Cylinder(1.0, 1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cylinder.setHeight(0.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                cylinder.setHeight(-2.0);
            });
        }
        
        @Test
        @DisplayName("Exception messages are descriptive")
        void exceptionMessagesAreDescriptive() {
            Exception radiusException = assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(0.0, 1.0);
            });
            assertTrue(radiusException.getMessage().toLowerCase().contains("radius"));
            
            Exception heightException = assertThrows(IllegalArgumentException.class, () -> {
                new Cylinder(1.0, 0.0);
            });
            assertTrue(heightException.getMessage().toLowerCase().contains("height"));
        }
    }
    
    @Nested
    @DisplayName("Inheritance Testing")
    class InheritanceTests {
        
        @Test
        @DisplayName("Cylinder works as Shape3D reference")
        void cylinderAsShape3D() {
            Shape3D shape = new Cylinder("Poly Cylinder", "Green", 2.0, 3.0);
            
            assertEquals("Poly Cylinder", shape.getName());
            assertEquals("Green", shape.getColor());
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Test calculations through base class interface
            double expectedVolume = Math.PI * Math.pow(2.0, 2) * 3.0;
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Cylinder implements ThreeDimensionalShape interface")
        void cylinderImplementsInterface() {
            ThreeDimensionalShape shape = new Cylinder(1.5, 2.5);
            
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            double expectedVolume = Math.PI * Math.pow(1.5, 2) * 2.5;
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Cylinder toString works through inheritance")
        void toStringThroughInheritance() {
            Shape3D shape = new Cylinder("Inherited", "Yellow", 1.5, 2.5);
            String result = shape.toString();
            
            assertTrue(result.contains("Inherited"));
            assertTrue(result.contains("Yellow"));
            assertTrue(result.contains("1.50"));
            assertTrue(result.contains("2.50"));
        }
        
        @Test
        @DisplayName("Inherited name and color properties work correctly")
        void inheritedPropertiesWork() {
            Cylinder cylinder = new Cylinder("Test Name", "Test Color", 2.0, 3.0);
            
            cylinder.setName("New Name");
            cylinder.setColor("New Color");
            
            assertEquals("New Name", cylinder.getName());
            assertEquals("New Color", cylinder.getColor());
        }
    }
    
    @Nested
    @DisplayName("Cylinder-Specific Features Tests")
    class CylinderSpecificTests {
        
        @Test
        @DisplayName("Base area formula verification")
        void baseAreaFormulaVerification() {
            double[] testRadii = {1.0, 2.0, 3.5, 10.0};
            
            for (double radius : testRadii) {
                Cylinder cylinder = new Cylinder(radius, 1.0);
                double expectedBaseArea = Math.PI * Math.pow(radius, 2);
                assertEquals(expectedBaseArea, cylinder.getBaseArea(), DELTA,
                    "Base area incorrect for radius " + radius);
            }
        }
        
        @Test
        @DisplayName("Lateral surface area formula verification")
        void lateralSurfaceAreaFormulaVerification() {
            double radius = 3.0;
            double height = 4.0;
            Cylinder cylinder = new Cylinder(radius, height);
            double expectedLateralArea = 2 * Math.PI * radius * height;
            
            assertEquals(expectedLateralArea, cylinder.getLateralSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Total surface area components verification")
        void totalSurfaceAreaComponents() {
            double radius = 2.0;
            double height = 5.0;
            Cylinder cylinder = new Cylinder(radius, height);
            
            double baseArea = cylinder.getBaseArea();
            double lateralArea = cylinder.getLateralSurfaceArea();
            double totalSurfaceArea = cylinder.getSurfaceArea();
            
            // Total surface area should equal 2 * base area + lateral area
            assertEquals(2 * baseArea + lateralArea, totalSurfaceArea, DELTA);
        }
        
        @Test
        @DisplayName("Diameter relationship verification")
        void diameterRelationshipVerification() {
            double radius = 7.5;
            Cylinder cylinder = new Cylinder(radius, 1.0);
            
            assertEquals(2 * radius, cylinder.getDiameter(), DELTA);
            assertEquals(2 * cylinder.getRadius(), cylinder.getDiameter(), DELTA);
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Multiple cylinders have independent properties")
        void independentCylinders() {
            Cylinder cylinder1 = new Cylinder("Cylinder1", "Red", 1.0, 2.0);
            Cylinder cylinder2 = new Cylinder("Cylinder2", "Blue", 2.0, 1.0);
            
            assertNotEquals(cylinder1.getRadius(), cylinder2.getRadius());
            assertNotEquals(cylinder1.getHeight(), cylinder2.getHeight());
            assertNotEquals(cylinder1.getVolume(), cylinder2.getVolume());
        }
        
        @Test
        @DisplayName("Cylinder calculations remain consistent after property changes")
        void calculationsAfterPropertyChanges() {
            Cylinder cylinder = new Cylinder(2.0, 3.0);
            double originalVolume = cylinder.getVolume();
            
            cylinder.setName("Changed Name");
            cylinder.setColor("Changed Color");
            
            // Volume should remain the same since dimensions didn't change
            assertEquals(originalVolume, cylinder.getVolume(), DELTA);
            
            // Change dimensions and verify volume changes
            cylinder.setRadius(3.0);
            assertNotEquals(originalVolume, cylinder.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Scaling properties work correctly")
        void scalingProperties() {
            Cylinder smallCylinder = new Cylinder(1.0, 1.0);
            Cylinder doubledRadius = new Cylinder(2.0, 1.0);
            Cylinder doubledHeight = new Cylinder(1.0, 2.0);
            
            // When radius doubles (height constant), volume increases by factor of 4
            assertEquals(4.0, doubledRadius.getVolume() / smallCylinder.getVolume(), DELTA);
            
            // When height doubles (radius constant), volume increases by factor of 2
            assertEquals(2.0, doubledHeight.getVolume() / smallCylinder.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Cylinder vs cube volume comparison")
        void cylinderVsCubeComparison() {
            // Compare cylinder inscribed in cube vs cube
            double sideLength = 2.0;
            Cylinder inscribed = new Cylinder(sideLength / 2, sideLength); // radius = side/2
            
            // Inscribed cylinder should have smaller volume than cube
            double cubeVolume = Math.pow(sideLength, 3);
            assertTrue(inscribed.getVolume() < cubeVolume);
        }
    }
}