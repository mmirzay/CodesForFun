package structural.decorator.decoratorClasses;

import structural.decorator.interfaces.Report;

public class NameColumnDecorator extends ColumnDecorator {

	public NameColumnDecorator(Report report) {
		super(report);
	}

	@Override
	public String getColumns() {
		return decorate(super.getColumns());
	}

	private String decorate(String c) {
		return c + " | Name";
	}

}
