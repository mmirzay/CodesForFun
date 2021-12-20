package structural.decorator.decoratorClasses;

import structural.decorator.interfaces.Report;

public abstract class ColumnDecorator implements Report {
	private Report report;

	public ColumnDecorator(Report report) {
		this.report = report;
	}

	@Override
	public String getColumns() {
		return report.getColumns();
	}

}
