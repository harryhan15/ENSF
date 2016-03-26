#include <iostream>
using namespace std;

#include "Point.h"
#include "Shape.h"
#include "Square.h"
#include "Rectangle.h"
#include "Circle.h"
#include "CornerCut.h"
#include "GraphicsWorld.h"

GraphicsWorld::GraphicsWorld() {}

void GraphicsWorld::run()
{
	cout << "\nThis program has been written by: Harry Han and Yida Xu\n";
	cout << "\nSubmitted at: March 28 , 2016\n";
	cout << "\nTesting Functions in class Point:\n";

	//Part 1

	//Point
	Point m(6, 8);
	Point n(6, 8);
	cout << "The number of points so far is: " << Point::counter() << "\n";

	n.setXCoord(9);
	m.display();
	n.display();
	cout << "The distance between the two points is: " << m.distance(n) << "\n";

	//Square
	cout << "\nTesting Functions in class Square:\n";
	Square s((char*)"SQUARE - S", 5, 7, 12);
	s.display();
	cout << "The area of " << s.getName() << " is: " << s.area() << "\n";
	cout << "The perimeter of " << s.getName() << " is: " << s.perimeter() << "\n";

	//Rectangle
	cout << "\nTesting Functions in class Rectangle:" <<endl;
	Rectangle a(5, 7, 12, 15, (char*)"RECTANGLE A");
	a.display();
	Rectangle b(16 , 7, 8, 9, (char*)"RECTANGLE B");
	b.display();
	cout << "The area of " << a.getName() <<" is: "<< a.area() << "\n";
	cout << "The perimeter of " << a.getName() <<" is: "<< a.perimeter() << "\n";
	double d = a.distance(b);
	cout << "\nThe distance between two rectangles is: " << d << endl;

	//Copy Constructor
	cout << "\nTesting copy constructor in class Rectangle:" <<endl;
	Rectangle rec1 = a;
	rec1.display();

	//Assignment Operator
	cout << "\nTesting assignment operator in class Rectangle:" <<endl;
	Rectangle rec2 (3, 4, 11, 7, (char*)"RECTANGLE rec2");
	rec2 = a;
	rec2.display();

	//Circle
	cout << "\nTesting Functions in class Circle:" << endl;
	Circle c (3, 5, 9, (char*)"CIRCLE C");
	c.display();
	cout << "the area of " << c.getName() <<" is: "<< c.area() << endl;
	cout << "the perimeter of " << c.getName() << " is: "<< c.perimeter() << endl;
	d = a.distance(c);
	cout << "\nThe distance between rectangle a and circle c is: " <<d;

	//Part 2
	cout << "\n\nPart 2\n";
	CornerCut rc (6, 5, 10, 12, 9, (char*)"CornerCut rc");
	rc.display();
	cout << "The area of " << rc.getName() << " is: " << rc.area() << endl;
	cout << "The perimeter of " << rc.getName() << " is: "<< rc.perimeter() << endl;
	d = rc.Circle::distance(c);
	cout << "\nThe distance between rc and c is: " <<d;


	// Using array of Shape pointers:
	Shape* sh[4];

	sh[0] = &s;
	sh[1] = &a;
	sh [2] = &c;
	sh [3] = &rc;

	sh [0]->display();
	cout << "\nThe area of "<< sh[0]->getName() << "is: "<< sh[0]->area();
	cout << "\nThe perimeter of " << sh[0]->getName () << " is: "<< sh[0]->perimeter();

	sh [1]->display();
	cout << "\nThe area of "<< sh[1]->getName() << "is: "<< sh[1]->area();
	cout << "\nThe perimeter of " << sh[0]->getName () << " is: "<< sh[1]->perimeter();

	sh [2]->display();
	cout << "\nThe area of "<< sh[2]->getName() << "is: "<< sh[2]->area();
	cout << "\nThe circumference of " << sh[2]->getName ()<< " is: "<< sh[2]->perimeter();

	sh [3]->display();
	cout << "\nThe area of "<< sh[3]->getName() << "is: "<< sh[3]->area();
	cout << "\nThe perimeter of " << sh[3]->getName () << " is: "<< sh[3]->perimeter();

	cout << "\n\nTesting copy constructor in class CornerCut:" <<endl;
	CornerCut cc = rc;
	cc.display();

	cout << "\nTesting assignment operator in class CornerCut:" <<endl;
	CornerCut cc2(2, 5, 100, 12, 9, (char*)"CornerCut cc2");
	cc2.display();
	cc2 = cc;
	cc2.display(); 
	
	cout << "\n.. Done Testing.\n\n";
}