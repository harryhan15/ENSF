#include <iostream>
#include "Shape.h"
#include "Point.h"
#include "CornerCut.h"
#include "Rectangle.h"
#include "Circle.h"
#include <string.h>

using namespace std;

CornerCut::CornerCut(double x, double y, double sideA, double sideB, double radius, char* shapeName) : Rectangle(x, y, sideA, sideB, shapeName), Circle(x, y, radius, shapeName), Shape(x, y, shapeName) {}

CornerCut::~CornerCut() {}

CornerCut::CornerCut(CornerCut& c) : Rectangle(c.getOrigin().getXCoord(), c.getOrigin().getYCoord(), c.getSideA(), c.getSideB(), c.getName()),
										   Circle(c.getOrigin().getXCoord(), c.getOrigin().getYCoord(), c.getRadius(), c.getName()),
										   Shape(c.getOrigin().getXCoord(), c.getOrigin().getYCoord(), c.getName()) {}

CornerCut & CornerCut::operator=(CornerCut& c) {
	if (this == &c) {
		return *this;
	}

	delete[] getName();

	setName(c.getName());
	setOrigin(c.getOrigin());

	setSideA(c.getSideA());
	setSideB(c.getSideB());

	setRadius(c.getRadius());
}

double CornerCut::area() {
	return Rectangle::area() - 0.25 * Circle::area();
}

double CornerCut::perimeter() {
	return Rectangle::perimeter() - 0.25 * Circle::perimeter();
}

char* CornerCut::getName()
{
	return Rectangle::getName();
}

void CornerCut::display() {
	Rectangle::display();
	cout << "Width: " << Rectangle::getSideA() << "\n";
	cout << "Length: " << Rectangle::getSideB() << "\n";
	cout << "Radius of the cut: " << Circle::getRadius() << "\n";
}