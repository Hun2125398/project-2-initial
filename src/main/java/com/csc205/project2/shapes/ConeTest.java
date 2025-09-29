package com.csc205.project2.shapes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for Cone
 * Tests basic functionality, calculation accuracy, boundary conditions,
 * input validation, and inheritance behavior
 */
@DisplayName("Cone Tests")
class ConeTest {
    
    private static final double DELTA = 0.001; // Tolerance for floating point comparisons
    
    @Nested
    @DisplayName("Basic Functionality Tests")
    class BasicFunctionalityTests {
        
        @Test
        @DisplayName("Default constructor creates cone with default values")
        void defaultConstructor() {
            Cone cone = new Cone();
            
            assertEquals("Cone", cone.getName());
            assertEquals("Unknown Color", cone.getColor());
            assertEquals(1.0, cone.getRadius(), DELTA);
            assertEquals(1.0, cone.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Constructor with radius and height sets correct values")
        void constructorWithDimensions() {
            double radius = 3.0;
            double height = 4.0;
            Cone cone = new Cone(radius, height);
            
            assertEquals("Cone", cone.getName());
            assertEquals("Unknown Color", cone.getColor());
            assertEquals(radius, cone.getRadius(), DELTA);
            assertEquals(height, cone.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Full constructor sets all values correctly")
        void fullConstructor() {
            String name = "Test Cone";
            String color = "Red";
            double radius = 2.5;
            double height = 6.0;
            
            Cone cone = new Cone(name, color, radius, height);
            
            assertEquals(name, cone.getName());
            assertEquals(color, cone.getColor());
            assertEquals(radius, cone.getRadius(), DELTA);
            assertEquals(height, cone.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Radius getter and setter work correctly")
        void radiusGetterSetter() {
            Cone cone = new Cone();
            double newRadius = 5.5;
            
            cone.setRadius(newRadius);
            assertEquals(newRadius, cone.getRadius(), DELTA);
        }
        
        @Test
        @DisplayName("Height getter and setter work correctly")
        void heightGetterSetter() {
            Cone cone = new Cone();
            double newHeight = 8.0;
            
            cone.setHeight(newHeight);
            assertEquals(newHeight, cone.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Slant height calculation is correct")
        void slantHeightCalculation() {
            // 3-4-5 right triangle: radius=3, height=4, slant height=5
            double radius = 3.0;
            double height = 4.0;
            Cone cone = new Cone(radius, height);
            
            assertEquals(5.0, cone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Diameter calculation is correct")
        void diameterCalculation() {
            double radius = 6.0;
            Cone cone = new Cone(radius, 1.0);
            
            assertEquals(2 * radius, cone.getDiameter(), DELTA);
        }
        
        @Test
        @DisplayName("Base area calculation is correct")
        void baseAreaCalculation() {
            double radius = 4.0;
            Cone cone = new Cone(radius, 3.0);
            double expectedBaseArea = Math.PI * Math.pow(radius, 2);
            
            assertEquals(expectedBaseArea, cone.getBaseArea(), DELTA);
        }
        
        @Test
        @DisplayName("Lateral surface area calculation is correct")
        void lateralSurfaceAreaCalculation() {
            double radius = 3.0;
            double height = 4.0;
            Cone cone = new Cone(radius, height);
            double slantHeight = cone.getSlantHeight(); // 5.0
            double expectedLateralArea = Math.PI * radius * slantHeight;
            
            assertEquals(expectedLateralArea, cone.getLateralSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("toString method returns properly formatted string")
        void toStringFormatting() {
            Cone cone = new Cone("Test Cone", "Green", 2.0, 3.0);
            String result = cone.toString();
            
            assertTrue(result.contains("Test Cone"));
            assertTrue(result.contains("Green"));
            assertTrue(result.contains("2.00"));
            assertTrue(result.contains("3.00"));
        }
    }
    
    @Nested
    @DisplayName("Calculation Accuracy Tests")
    class CalculationAccuracyTests {
        
        @Test
        @DisplayName("Volume calculation for radius 3, height 4 is accurate")
        void coneVolumeCalculation() {
            // Volume of cone = (1/3)πr²h = (1/3)π(3²)(4) = 12π ≈ 37.699
            double radius = 3.0;
            double height = 4.0;
            Cone cone = new Cone(radius, height);
            double expectedVolume = (1.0 / 3.0) * Math.PI * Math.pow(radius, 2) * height;
            
            assertEquals(expectedVolume, cone.getVolume(), DELTA);
            assertEquals(37.699, cone.getVolume(), 0.01);
        }
        
        @Test
        @DisplayName("Surface area calculation for radius 1, height 2 is accurate")
        void coneSurfaceAreaCalculation() {
            // Surface area = πr² + πr√(r² + h²) = π + π√5 ≈ 3.142 + 7.024 ≈ 10.166
            double radius = 1.0;
            double height = 2.0;
            Cone cone = new Cone(radius, height);
            
            double baseArea = Math.PI * Math.pow(radius, 2);
            double slantHeight = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
            double lateralArea = Math.PI * radius * slantHeight;
            double expectedSurfaceArea = baseArea + lateralArea;
            
            assertEquals(expectedSurfaceArea, cone.getSurfaceArea(), DELTA);
            assertEquals(10.166, cone.getSurfaceArea(), 0.01);
        }
        
        @Test
        @DisplayName("Unit cone calculations are correct")
        void unitConeCalculations() {
            // Unit cone (radius = 1, height = 1)
            Cone unitCone = new Cone(1.0, 1.0);
            
            double expectedVolume = Math.PI / 3.0;
            double expectedSlantHeight = Math.sqrt(2);
            
            assertEquals(expectedVolume, unitCone.getVolume(), DELTA);
            assertEquals(expectedSlantHeight, unitCone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Tall cone calculations are accurate")
        void tallConeCalculations() {
            double radius = 2.0;
            double height = 10.0;
            Cone cone = new Cone(radius, height);
            
            double expectedVolume = (1.0 / 3.0) * Math.PI * 4.0 * 10.0; // (1/3)π(4)(10)
            assertEquals(expectedVolume, cone.getVolume(), DELTA);
            
            double expectedSlantHeight = Math.sqrt(4 + 100); // √104
            assertEquals(expectedSlantHeight, cone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Wide cone calculations are accurate")
        void wideConeCalculations() {
            double radius = 10.0;
            double height = 2.0;
            Cone cone = new Cone(radius, height);
            
            double expectedVolume = (1.0 / 3.0) * Math.PI * 100.0 * 2.0; // (1/3)π(100)(2)
            assertEquals(expectedVolume, cone.getVolume(), DELTA);
            
            double expectedSlantHeight = Math.sqrt(100 + 4); // √104
            assertEquals(expectedSlantHeight, cone.getSlantHeight(), DELTA);
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
            Cone cone = new Cone(smallRadius, smallHeight);
            
            assertTrue(cone.getVolume() > 0);
            assertTrue(cone.getSurfaceArea() > 0);
            assertTrue(cone.getSlantHeight() > 0);
            assertEquals(smallRadius, cone.getRadius(), DELTA);
            assertEquals(smallHeight, cone.getHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Large dimensions work correctly")
        void largeDimensions() {
            double largeRadius = 1000.0;
            double largeHeight = 1000.0;
            Cone cone = new Cone(largeRadius, largeHeight);
            
            assertTrue(cone.getVolume() > 0);
            assertTrue(cone.getSurfaceArea() > 0);
            assertTrue(cone.getSlantHeight() > largeRadius);
            assertTrue(cone.getSlantHeight() > largeHeight);
        }
        
        @Test
        @DisplayName("Extreme aspect ratios work correctly")
        void extremeAspectRatios() {
            // Very tall, thin cone
            Cone tallThin = new Cone(0.1, 100.0);
            assertTrue(tallThin.getVolume() > 0);
            assertTrue(tallThin.getSurfaceArea() > 0);
            assertTrue(tallThin.getSlantHeight() > tallThin.getHeight()); // Slant > height for any cone
            
            // Very short, wide cone
            Cone shortWide = new Cone(100.0, 0.1);
            assertTrue(shortWide.getVolume() > 0);
            assertTrue(shortWide.getSurfaceArea() > 0);
            assertTrue(shortWide.getSlantHeight() > shortWide.getRadius()); // Slant > radius for any cone
        }
    }
    
    @Nested
    @DisplayName("Input Validation Tests")
    class InputValidationTests {
        
        @Test
        @DisplayName("Constructor with zero radius throws exception")
        void constructorWithZeroRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cone(0.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with zero height throws exception")
        void constructorWithZeroHeight() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cone(1.0, 0.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative radius throws exception")
        void constructorWithNegativeRadius() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cone(-2.0, 1.0);
            });
        }
        
        @Test
        @DisplayName("Constructor with negative height throws exception")
        void constructorWithNegativeHeight() {
            assertThrows(IllegalArgumentException.class, () -> {
                new Cone(1.0, -3.0);
            });
        }
        
        @Test
        @DisplayName("Radius setter with invalid values throws exception")
        void radiusSetterValidation() {
            Cone cone = new Cone(1.0, 1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cone.setRadius(0.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                cone.setRadius(-1.5);
            });
        }
        
        @Test
        @DisplayName("Height setter with invalid values throws exception")
        void heightSetterValidation() {
            Cone cone = new Cone(1.0, 1.0);
            
            assertThrows(IllegalArgumentException.class, () -> {
                cone.setHeight(0.0);
            });
            
            assertThrows(IllegalArgumentException.class, () -> {
                cone.setHeight(-2.0);
            });
        }
        
        @Test
        @DisplayName("Exception messages are descriptive")
        void exceptionMessagesAreDescriptive() {
            Exception radiusException = assertThrows(IllegalArgumentException.class, () -> {
                new Cone(0.0, 1.0);
            });
            assertTrue(radiusException.getMessage().toLowerCase().contains("radius"));
            
            Exception heightException = assertThrows(IllegalArgumentException.class, () -> {
                new Cone(1.0, 0.0);
            });
            assertTrue(heightException.getMessage().toLowerCase().contains("height"));
        }
    }
    
    @Nested
    @DisplayName("Inheritance Testing")
    class InheritanceTests {
        
        @Test
        @DisplayName("Cone works as Shape3D reference")
        void coneAsShape3D() {
            Shape3D shape = new Cone("Poly Cone", "Purple", 3.0, 4.0);
            
            assertEquals("Poly Cone", shape.getName());
            assertEquals("Purple", shape.getColor());
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            // Test calculations through base class interface
            double expectedVolume = (1.0 / 3.0) * Math.PI * 9.0 * 4.0; // (1/3)π(3²)(4)
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Cone implements ThreeDimensionalShape interface")
        void coneImplementsInterface() {
            ThreeDimensionalShape shape = new Cone(2.0, 3.0);
            
            assertTrue(shape.getVolume() > 0);
            assertTrue(shape.getSurfaceArea() > 0);
            
            double expectedVolume = (1.0 / 3.0) * Math.PI * 4.0 * 3.0; // (1/3)π(2²)(3)
            assertEquals(expectedVolume, shape.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Cone toString works through inheritance")
        void toStringThroughInheritance() {
            Shape3D shape = new Cone("Inherited", "Magenta", 1.5, 2.5);
            String result = shape.toString();
            
            assertTrue(result.contains("Inherited"));
            assertTrue(result.contains("Magenta"));
            assertTrue(result.contains("1.50"));
            assertTrue(result.contains("2.50"));
        }
        
        @Test
        @DisplayName("Inherited name and color properties work correctly")
        void inheritedPropertiesWork() {
            Cone cone = new Cone("Test Name", "Test Color", 2.0, 3.0);
            
            cone.setName("New Name");
            cone.setColor("New Color");
            
            assertEquals("New Name", cone.getName());
            assertEquals("New Color", cone.getColor());
        }
    }
    
    @Nested
    @DisplayName("Cone-Specific Features Tests")
    class ConeSpecificTests {
        
        @Test
        @DisplayName("Slant height formula verification")
        void slantHeightFormulaVerification() {
            // Test with known Pythagorean triples
            double[][] testCases = {
                {3.0, 4.0, 5.0},   // 3-4-5 triangle
                {5.0, 12.0, 13.0}, // 5-12-13 triangle
                {8.0, 15.0, 17.0}, // 8-15-17 triangle
                {1.0, 1.0, Math.sqrt(2)} // 1-1-√2 triangle
            };
            
            for (double[] testCase : testCases) {
                Cone cone = new Cone(testCase[0], testCase[1]);
                assertEquals(testCase[2], cone.getSlantHeight(), DELTA,
                    "Slant height incorrect for radius " + testCase[0] + ", height " + testCase[1]);
            }
        }
        
        @Test
        @DisplayName("Base area formula verification")
        void baseAreaFormulaVerification() {
            double[] testRadii = {1.0, 2.0, 3.5, 10.0};
            
            for (double radius : testRadii) {
                Cone cone = new Cone(radius, 1.0);
                double expectedBaseArea = Math.PI * Math.pow(radius, 2);
                assertEquals(expectedBaseArea, cone.getBaseArea(), DELTA,
                    "Base area incorrect for radius " + radius);
            }
        }
        
        @Test
        @DisplayName("Lateral surface area formula verification")
        void lateralSurfaceAreaFormulaVerification() {
            double radius = 6.0;
            double height = 8.0;
            Cone cone = new Cone(radius, height);
            double slantHeight = cone.getSlantHeight(); // 10.0
            double expectedLateralArea = Math.PI * radius * slantHeight;
            
            assertEquals(expectedLateralArea, cone.getLateralSurfaceArea(), DELTA);
            assertEquals(60 * Math.PI, cone.getLateralSurfaceArea(), DELTA);
        }
        
        @Test
        @DisplayName("Total surface area components verification")
        void totalSurfaceAreaComponents() {
            double radius = 5.0;
            double height = 12.0;
            Cone cone = new Cone(radius, height);
            
            double baseArea = cone.getBaseArea();
            double lateralArea = cone.getLateralSurfaceArea();
            double totalSurfaceArea = cone.getSurfaceArea();
            
            // Total surface area should equal base area + lateral area
            assertEquals(baseArea + lateralArea, totalSurfaceArea, DELTA);
        }
        
        @Test
        @DisplayName("Geometric relationships verification")
        void geometricRelationshipsVerification() {
            Cone cone = new Cone(7.0, 24.0);
            
            // Slant height should always be greater than both radius and height
            assertTrue(cone.getSlantHeight() > cone.getRadius());
            assertTrue(cone.getSlantHeight() > cone.getHeight());
            
            // Pythagorean theorem: slant² = radius² + height²
            double expectedSlantSquared = Math.pow(cone.getRadius(), 2) + Math.pow(cone.getHeight(), 2);
            double actualSlantSquared = Math.pow(cone.getSlantHeight(), 2);
            assertEquals(expectedSlantSquared, actualSlantSquared, DELTA);
            
            // Diameter should equal 2 * radius
            assertEquals(2 * cone.getRadius(), cone.getDiameter(), DELTA);
        }
    }
    
    @Nested
    @DisplayName("Edge Cases and Special Scenarios")
    class EdgeCasesTests {
        
        @Test
        @DisplayName("Multiple cones have independent properties")
        void independentCones() {
            Cone cone1 = new Cone("Cone1", "Red", 2.0, 3.0);
            Cone cone2 = new Cone("Cone2", "Blue", 3.0, 2.0);
            
            assertNotEquals(cone1.getRadius(), cone2.getRadius());
            assertNotEquals(cone1.getHeight(), cone2.getHeight());
            assertNotEquals(cone1.getVolume(), cone2.getVolume());
            assertNotEquals(cone1.getSurfaceArea(), cone2.getSurfaceArea());
        }
        
        @Test
        @DisplayName("Cone calculations remain consistent after property changes")
        void calculationsAfterPropertyChanges() {
            Cone cone = new Cone(3.0, 4.0);
            double originalVolume = cone.getVolume();
            double originalSlantHeight = cone.getSlantHeight();
            
            cone.setName("Changed Name");
            cone.setColor("Changed Color");
            
            // Volume and slant height should remain the same since dimensions didn't change
            assertEquals(originalVolume, cone.getVolume(), DELTA);
            assertEquals(originalSlantHeight, cone.getSlantHeight(), DELTA);
            
            // Change dimensions and verify calculations change
            cone.setRadius(4.0);
            assertNotEquals(originalVolume, cone.getVolume(), DELTA);
            assertNotEquals(originalSlantHeight, cone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Scaling properties work correctly")
        void scalingProperties() {
            Cone originalCone = new Cone(2.0, 3.0);
            Cone doubledCone = new Cone(4.0, 6.0);
            
            // When all dimensions double, volume increases by factor of 8 (2³)
            assertEquals(8.0, doubledCone.getVolume() / originalCone.getVolume(), DELTA);
            
            // When all dimensions double, surface area increases by factor of 4 (2²)
            assertEquals(4.0, doubledCone.getSurfaceArea() / originalCone.getSurfaceArea(), DELTA);
            
            // Slant height should double as well
            assertEquals(2.0, doubledCone.getSlantHeight() / originalCone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Cone vs cylinder volume comparison")
        void coneVsCylinderComparison() {
            double radius = 5.0;
            double height = 6.0;
            
            Cone cone = new Cone(radius, height);
            // For comparison, imagine a cylinder with same base and height
            double cylinderVolume = Math.PI * Math.pow(radius, 2) * height;
            
            // Cone volume should be exactly 1/3 of cylinder volume
            assertEquals(cylinderVolume / 3.0, cone.getVolume(), DELTA);
        }
        
        @Test
        @DisplayName("Right circular cone properties")
        void rightCircularConeProperties() {
            Cone cone = new Cone(5.0, 12.0); // 5-12-13 Pythagorean triple
            
            // Verify all geometric properties are consistent
            assertEquals(13.0, cone.getSlantHeight(), DELTA);
            assertEquals(10.0, cone.getDiameter(), DELTA);
            assertEquals(25 * Math.PI, cone.getBaseArea(), DELTA);
            assertEquals(65 * Math.PI, cone.getLateralSurfaceArea(), DELTA);
            assertEquals(90 * Math.PI, cone.getSurfaceArea(), DELTA); // 25π + 65π
            assertEquals(100 * Math.PI, cone.getVolume(), DELTA); // (1/3)π(25)(12)
        }
        
        @Test
        @DisplayName("Mathematical precision with floating point")
        void floatingPointPrecision() {
            // Test with values that might cause precision issues
            Cone cone = new Cone(0.1, 0.2);
            
            assertTrue(cone.getVolume() > 0);
            assertTrue(cone.getSurfaceArea() > 0);
            assertTrue(cone.getSlantHeight() > 0);
            
            // Verify precision relationships still hold
            double expectedSlant = Math.sqrt(0.01 + 0.04); // √0.05
            assertEquals(expectedSlant, cone.getSlantHeight(), DELTA);
        }
        
        @Test
        @DisplayName("Cone degenerate case analysis")
        void degenerateCases() {
            // Very flat cone (large radius, small height)
            Cone flatCone = new Cone(100.0, 0.1);
            assertTrue(flatCone.getVolume() > 0);
            assertTrue(flatCone.getSlantHeight() > flatCone.getRadius()); // Still > radius
            
            // Very tall cone (small radius, large height)
            Cone tallCone = new Cone(0.1, 100.0);
            assertTrue(tallCone.getVolume() > 0);
            assertTrue(tallCone.getSlantHeight() > tallCone.getHeight()); // Still > height
        }
        
        @Test
        @DisplayName("Cone surface area distribution")
        void surfaceAreaDistribution() {
            // Test cases where base area vs lateral area have different ratios
            
            // Flat cone: base area should dominate
            Cone flatCone = new Cone(10.0, 1.0);
            assertTrue(flatCone.getBaseArea() > flatCone.getLateralSurfaceArea());
            
            // Tall cone: lateral area should dominate  
            Cone tallCone = new Cone(1.0, 10.0);
            assertTrue(tallCone.getLateralSurfaceArea() > tallCone.getBaseArea());
        }
        
        @Test
        @DisplayName("Volume scaling with different dimension changes")
        void volumeScalingAnalysis() {
            Cone baseCone = new Cone(2.0, 3.0);
            
            // Double only radius: volume should increase by factor of 4
            Cone doubleRadius = new Cone(4.0, 3.0);
            assertEquals(4.0, doubleRadius.getVolume() / baseCone.getVolume(), DELTA);
            
            // Double only height: volume should increase by factor of 2
            Cone doubleHeight = new Cone(2.0, 6.0);
            assertEquals(2.0, doubleHeight.getVolume() / baseCone.getVolume(), DELTA);
        }
    }
}