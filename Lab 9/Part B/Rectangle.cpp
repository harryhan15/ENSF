#include <iostream>
#include "Shape.h"
#include "Point.h"
#include "Rectangle.h"
#include <string.h>

using namespace std;

Rectangle::Rectangle(double x, double y, double sideA, double sideB, char* shapeName) : Shape(x, y, shapeName) {
	side_a = sideA;
	side_b = sideB;
}

Rectangle::~Rectangle() {}

Rectangle::Rectangle(Rectangle& r) : Shape(r.getOrigin().getXCoord(), r.getOrigin().getYCoord(), r.getName()) {
	side_a = r.getSideA();
	side_b = r.getSideB();
}

Rectangle & Rectangle::operator=(Rectangle& r) {
	if (this == &r) {
		return *this;
	}

	delete[] getName();

	setName(r.getName());
	setOrigin(r.getOrigin());

	side_a = r.getSideA();
	side_b = r.getSideB();
}

double Rectangle::area() {
	return side_a * side_b;
}

double Rectangle::perimeter() {
	return side_a * 2 + side_b * 2;
}

double Rectangle::getSideA() {
	return side_a;
}

void Rectangle::setSideA(double sideA) {
	side_a = sideA;
}

double Rectangle::getSideB() {
	return side_b;
}

void Rectangle::setSideB(double sideB) {
	side_b = sideB;
}

void Rectangle::display() {
	Shape::display();
}