require 'buildr/git_auto_version'
require 'buildr/gpg'

desc 'timeservice: A simple EE service to get constant now within transaction'
define 'timeservice' do
  project.group = 'org.realityforge.timeservice'

  compile.options.source = '1.8'
  compile.options.target = '1.8'
  compile.options.lint = 'all'

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache_v2_license
  pom.add_github_project('realityforge/timeservice')
  pom.add_developer('realityforge', 'Peter Donald')

  compile.with :javaee_api, :javax_annotation

  package(:jar)
  package(:sources)
  package(:javadoc)
end
