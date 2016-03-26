#ifndef SHAPE_H
#define SHAPE_H

#include "Point.h"

class Shape {
private:
	char* shapeName;
	Point origin;
public:
	Shape(double x, double y, char* name);
	~Shape();

	Point getOrigin();
	void setOrigin(Point p);

	char* getName();
	void setName(char* name);

	double distance(Shape& s1);
	static double distance(Shape& s1, Shape& s2);

	void move(double dx, double dy);

	void display();

	//Part 2 Additions
	virtual double area();
	virtual double perimeter();
};

#endif