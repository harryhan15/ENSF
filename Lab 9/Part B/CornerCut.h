#ifndef CORNERCUT_H
#define CORNERCUT_H

#include "Rectangle.h"
#include "Circle.h"
#include "Shape.h"

class CornerCut : public Rectangle, public Circle {
private:
	
public:
	CornerCut(double x, double y, double sideA, double sideB, double radius, char* shapeName);
	~CornerCut();
	CornerCut(CornerCut& c);
	
	CornerCut & operator=(CornerCut& c);

	double area();
	double perimeter();

	char* getName();

	void display();
};

#endif