#include <iostream>
#include "Shape.h"
#include "Point.h"
#include "Circle.h"
#include <cmath>
#include <math.h>
#include <string.h>

using namespace std;

Circle::Circle(double x, double y, double rad, char* shapeName) : Shape(x, y, shapeName){
	radius = rad;
}

double Circle::area() {
	return M_PI * pow(radius, 2);
}

double Circle::perimeter() {
	return 2 * M_PI * radius;
}

double Circle::getRadius() {
	return radius;
}

void Circle::setRadius(double rad) {
	radius = rad;
}

void Circle::display() {
	Shape::display();
}